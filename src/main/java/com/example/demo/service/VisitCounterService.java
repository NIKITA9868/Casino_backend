package com.example.demo.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class VisitCounterService {


    private final ConcurrentHashMap<String, AtomicLong> urlCounters = new ConcurrentHashMap<>();

    public void incrementCount(String url) {

        urlCounters.compute(url, (key, counter) -> {
            if (counter == null) {
                return new AtomicLong(1);
            }
            counter.incrementAndGet();
            return counter;
        });
    }

    public int getVisitCount(String url) {
        return urlCounters.getOrDefault(url, new AtomicLong(0)).intValue();
    }

    public int getTotalVisitCount() {
        return urlCounters.values().stream().mapToInt(AtomicLong::intValue).sum();
    }

    public Map<String, Integer> getAllVisitCounts() {
        return urlCounters.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().intValue()
                ));
    }
}