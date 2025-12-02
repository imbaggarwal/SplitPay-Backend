package com.splitpay.userservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "user-events", groupId = "splitpay-group")
    public void listen(String message){
        System.out.println("Kafka Consumer: Received Message -> " + message);
    }
}
