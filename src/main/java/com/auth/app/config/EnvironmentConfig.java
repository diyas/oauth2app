package com.auth.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class EnvironmentConfig {
    @Profile("dev")
    @Bean
    public String devProfile() {
        System.out.println("Development Profile");
        return "Development Profile";
    }

    @Profile("test")
    @Bean
    public String testProfile() {
        System.out.println("Testing Profile");
        return "Testing Profile";
    }
}
