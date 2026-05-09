package com.jkaninda.simpleapi.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.security")
public class SecurityProperties {

    @NotBlank(message = "app.security.key is required and cannot be empty")
    @NotEmpty(message = "app.security.key cannot be empty string")
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void validate() {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalStateException("Application startup failed: app.security.key is missing or empty.");
        }
        
        if (!"supersecretstring".equals(key)) {
            throw new IllegalStateException("Application startup failed: app.security.key is incorrect. Expected: supersecretstring");
        }
    }
}