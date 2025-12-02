package com.splitpay.userservice.controller;

import com.splitpay.userservice.model.User;
import com.splitpay.userservice.service.FeatureFlagService;
import com.splitpay.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final FeatureFlagService featureFlagService;

    public UserController(UserService userService, FeatureFlagService featureFlagService){
        this.userService = userService;
        this.featureFlagService = featureFlagService;
    }

    @PostMapping("/feature/{featureName}/{isEnabled}")
    public String setFeature(@PathVariable String featureName, @PathVariable boolean isEnabled){
        featureFlagService.setFeatureFlag(featureName, isEnabled);
        return "Feature " + featureName + " set to " + isEnabled;
    }

    @PostMapping("create")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUserByID(id);
    }

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello! SplitPay user Service is running.";
    }
}
