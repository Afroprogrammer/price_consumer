package com.example.price_consumer.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AlertProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "alerts";

    public AlertProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendAlert(String alertMessage) {
        kafkaTemplate.send(TOPIC, alertMessage);
    }
}
