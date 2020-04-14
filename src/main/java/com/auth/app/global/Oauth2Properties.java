package com.auth.app.global;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("oauth2")
public class Oauth2Properties {
    private String url;
    private String clientId;
    private String clientSecret;
    private int tokenExpired;
    private int refreshToken;

    public String getCredentials() {
        return this.getClientId() + ":" + this.getClientSecret();
    }
}
