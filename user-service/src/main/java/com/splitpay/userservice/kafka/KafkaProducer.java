package com.splitpay.userservice.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String message){
        System.out.println("Kafka Producer: Sending message to " + topic + " -> " + message);
        kafkaTemplate.send(topic, message);
    }
}
