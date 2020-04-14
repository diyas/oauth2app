package com.auth.app.payload.model;

import lombok.Data;

@Data
public class Credential {
    private String clientId;
    private String clientSecret;
}
