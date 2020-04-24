package com.auth.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.auth.app")
public class Oauth2appApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2appApplication.class, args);
    }

}
