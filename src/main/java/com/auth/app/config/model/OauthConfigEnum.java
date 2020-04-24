package com.auth.app.config.model;

public enum OauthConfigEnum {
    xd("tester"),
    CLIENT_SECRET("tester"),
    TOKEN_EXPIRED("50"),
    TOKEN_REFRESH("50"),
    TOKEN_URL("http://localhost:8080/oauth/token"),
    CHECK_TOKEN_URL("http://localhost:8080/oauth/check_token");

    private final String s;

    OauthConfigEnum(String s) {
        this.s = s;
    }
}
