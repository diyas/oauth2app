package com.auth.app.service;

import java.util.Set;

public interface OAuth2AccessTokenRedis {

    public Set<byte[]> getKeyRedis(String key);
}
