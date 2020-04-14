package com.auth.app;

import com.auth.app.global.SettingEnum;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.auth.app")
public class Oauth2appApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2appApplication.class, args);
        System.out.println(SettingEnum.GRANT_TYPE.value);
    }

}
