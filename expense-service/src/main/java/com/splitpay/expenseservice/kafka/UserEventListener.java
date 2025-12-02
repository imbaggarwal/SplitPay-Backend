package com.splitpay.expenseservice.kafka;

import com.splitpay.expenseservice.model.Wallet;
import com.splitpay.expenseservice.repository.WalletRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserEventListener {

    private final WalletRepository walletRepository;
    public UserEventListener(WalletRepository walletRepository){
        this.walletRepository = walletRepository;
    }

    @KafkaListener(topics = "user-events", groupId = "splitpay-expense-group")
    public void handleUserCreated(String message){
        System.out.println("EXPENSE-SERVICE: Received User Event -> " + message);

        try{
            String idPart = message.split(": ")[1];
            Long userId = Long.parseLong(idPart);

            Wallet wallet = new Wallet();
            wallet.setUserId(userId);
            wallet.setBalance(BigDecimal.ZERO);
            wallet.setCurrency("USD");

            walletRepository.save(wallet);
            System.out.println("EXPENSE-SERVICE: Created Wallet for User ID: " + userId);
        }
        catch(Exception e){
            System.err.println("Error creating wallet: " + e.getMessage());
        }
    }
}
