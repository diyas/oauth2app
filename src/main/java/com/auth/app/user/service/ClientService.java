package com.auth.app.user.service;

import com.auth.app.Utility.Utility;
import com.auth.app.repository.ClientRepo;
import com.auth.app.user.model.Clients;
import com.auth.app.user.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

@Service
public class ClientService implements ClientDetailsService {

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Clients clients = clientRepo.findByClientId(clientId);
        String resourceIds = clients.getResourceIds();
        String scopes = clients.getScope();
        String grantTypes = clients.getAuthorizedGrantTypes();
        String authorities = clients.getAuthorities();
        Set<String> autoApproveScopes = Utility.setString(scopes, ",");


        BaseClientDetails base = new BaseClientDetails(clients.getClientId(), resourceIds, scopes, grantTypes, authorities);
        base.setClientSecret(clients.getClientSecret());
        base.setAccessTokenValiditySeconds(clients.getAccessTokenValidity());
        base.setRefreshTokenValiditySeconds(clients.getRefreshTokenValidity());
        base.setAdditionalInformation(new HashMap<>());
        base.setAutoApproveScopes(autoApproveScopes);
        return base;
    }
}
