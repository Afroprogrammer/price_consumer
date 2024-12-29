package com.example.price_consumer.service;

import com.example.price_consumer.model.PriceThreshold;
import com.example.price_consumer.model.AlertThresholdRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
    public class PriceConsumer {

    private final AlertThresholdRepository repository;
    private final AlertProducer alertProducer;
    private final AlertThresholdRepository alertThresholdRepository;

    public PriceConsumer(AlertThresholdRepository repository, AlertProducer alertProducer, AlertThresholdRepository alertThresholdRepository) {
        this.repository = repository;
        this.alertProducer = alertProducer;
        this.alertThresholdRepository = alertThresholdRepository;
    }
        @KafkaListener(topics = "price-updates", groupId = "alert-service")
        public void consumePriceUpdate(String priceUpdate) {
            System.out.println("Consumed price update: " + priceUpdate);
            // Process the price update (e.g., compare with thresholds)

            // Extract crypto name and price from the message
            String[] parts = priceUpdate.split(":");
            String crypto = parts[0].trim();
            double price = Double.parseDouble(parts[1].trim().split(" ")[0]);

            PriceThreshold threshold = repository.findByCrypto(crypto);
            if (threshold != null) {
                if (price < threshold.getLowerThreshold()|| price > threshold.getUpperThreshold()) {
                    String alertMessage = "ALERT: " + crypto + " price is " + price + " USD";
                    alertProducer.sendAlert(alertMessage);
                    System.out.println("Alert sent: " + alertMessage);
                }
            }
        }
    }
