package com.splitpay.userservice.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class FeatureFlagService {

    private final StringRedisTemplate redisTemplate;

    public FeatureFlagService(StringRedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    public boolean isFeatureEnabled(String featureName){
        String value = redisTemplate.opsForValue().get("feature: " + featureName);
        return Boolean.parseBoolean(value);
    }

    public void setFeatureFlag(String featureName, boolean isEnabled){
        redisTemplate.opsForValue().set("feature: " + featureName, String.valueOf(isEnabled));
    }
}
