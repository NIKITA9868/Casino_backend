package com.example.demo.controller;


import com.example.demo.service.VisitCounterService;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/visits")
public class VisitCounterController {
    private final VisitCounterService visitCounterService;

    public VisitCounterController(VisitCounterService visitCounterService) {
        this.visitCounterService = visitCounterService;
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getVisitCount(@RequestParam String url) {
        return ResponseEntity.ok(visitCounterService.getVisitCount(url));
    }

    @GetMapping("/total")
    public ResponseEntity<Map<String, Integer>> getTotalVisitCount() {
        return ResponseEntity.ok(
                Map.of("total", visitCounterService.getTotalVisitCount())
        );
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Integer>> getAllVisitCounts() {
        return ResponseEntity.ok(visitCounterService.getAllVisitCounts());
    }
}