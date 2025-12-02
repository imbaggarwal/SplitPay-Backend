package com.splitpay.userservice.service;

import com.splitpay.userservice.model.User;
import com.splitpay.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // Create a User
    public User createUser(User user){
        return userRepository.save(user);
    }

    // Get all Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get User by ID
    public User getUserByID(Long id){
        return userRepository.findById(id).orElse(null);
    }
}
