package com.auth.app.global;

public enum ErrorProvider {
    SUCCESS("success", 200),
    BAD_CREDENTIAL("bad_credential", 400),
    INVALID_TOKEN("invalid_token", 400);

    private final int code;
    private final String name;

    ErrorProvider(String name, int code) {
        this.code = code;
        this.name = name;
    }
}
