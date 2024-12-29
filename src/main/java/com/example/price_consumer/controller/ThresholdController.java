package com.example.price_consumer.controller;

import com.example.price_consumer.model.PriceThreshold;
import com.example.price_consumer.model.AlertThresholdRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/thresholds")
public class ThresholdController {
    private final AlertThresholdRepository repository;

    public ThresholdController(AlertThresholdRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<String> addThreshold(@RequestBody PriceThreshold threshold) {
        repository.save(threshold);
        return ResponseEntity.ok("Threshold saved for: " + threshold.getCrypto());
    }
}