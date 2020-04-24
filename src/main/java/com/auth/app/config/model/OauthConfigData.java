package com.auth.app.config.model;

import org.springframework.beans.factory.annotation.Autowired;

public class OauthConfigData {
    @Autowired
    OauthConfigRepo config;

    private String values;

    public OauthConfigData(OauthConfigEnum configEnum) {
        OauthConfig configData = config.findByConfigName(configEnum);
        this.values = configData.getValue();
    }

    public String getValue() {
        return this.values;
    }
}
