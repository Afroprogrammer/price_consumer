package com.example.price_consumer.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertThresholdRepository extends JpaRepository<PriceThreshold, Long> {
    PriceThreshold findByCrypto(String crypto);
}
