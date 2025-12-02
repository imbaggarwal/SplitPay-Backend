package com.splitpay.expenseservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserEventListener {

    @KafkaListener(topics = "user-events", groupId = "splitpay-expense-group")
    public void handleUserCreated(String message){
        System.out.println("EXPENSE-SERVICE: Received User Event -> " + message);
    }
}
