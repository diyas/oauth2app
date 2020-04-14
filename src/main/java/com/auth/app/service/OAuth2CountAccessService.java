package com.auth.app.service;


import com.auth.app.domain.OAuth2CountAccess;

public interface OAuth2CountAccessService {

    public OAuth2CountAccess findByClientId(String clientId);

    public void saveOAuth2CountAccess(OAuth2CountAccess oAuth2CountAccess);

    public void updateOAuth2CountAccess(OAuth2CountAccess oAuth2CountAccess);

}
