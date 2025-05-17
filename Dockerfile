FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /com.example.demo
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests spring-boot:repackage

FROM eclipse-temurin:21-jre-jammy
WORKDIR /com.example.demo
COPY --from=build /com.example.demo/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]