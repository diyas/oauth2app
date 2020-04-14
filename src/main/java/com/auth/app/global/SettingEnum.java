package com.auth.app.global;

public enum SettingEnum {
    GRANT_TYPE("password"),
    AUTHORIZATION_CODE("authorization_code"),
    REFRESH_TOKEN("refresh_token"),
    IMPLICIT("implicit"),
    SCOPE_READ("read"),
    SCOPE_WRITE("write"),
    TRUST("trust");

    public String value;

    SettingEnum(String value) {
        this.value = value;
    }
}
