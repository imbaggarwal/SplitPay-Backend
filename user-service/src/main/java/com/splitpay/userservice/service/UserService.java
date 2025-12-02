package com.splitpay.userservice.service;

import com.splitpay.userservice.model.User;
import com.splitpay.userservice.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FeatureFlagService featureFlagService;

    public UserService(UserRepository userRepository, FeatureFlagService featureFlagService){
        this.userRepository = userRepository;
        this.featureFlagService = featureFlagService;
    }

    // Create a User
    public User createUser(User user){
        if(featureFlagService.isFeatureEnabled("strict-email-validation")){
            System.out.println("Feature Flag is ON: Checking email domain...");

            if(!user.getEmail().endsWith("@splitpay.com")){
                throw new IllegalArgumentException("Only @splitpay.com emails are allowed!");
            }
        }
        else{
            System.out.println("Feature Flag is OFF: Allowing any email.");
        }
        return userRepository.save(user);
    }

    // Get all Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get User by ID
    @Cacheable(value = "users", key = "#id")
    public User getUserByID(Long id){
        System.out.println("fetching user from database for id: " + id);
        return userRepository.findById(id).orElse(null);
    }
}
