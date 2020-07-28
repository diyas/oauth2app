package com.auth.app.config;

import com.auth.app.global.Oauth2Properties;
import com.auth.app.global.SettingEnum;
import com.auth.app.handler.CustomOauthException;
import com.auth.app.user.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private Oauth2Properties oauth2Properties;

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserApprovalHandler userApprovalHandler;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ClientService clientDetailService;

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        return new JwtAccessTokenConverter();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.allowFormAuthenticationForClients().checkTokenAccess("isAuthenticated()");
    }

//    @Override
//    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
//
//        configurer.inMemory()
//                .withClient(oauth2Properties.getClientId())
//                .secret(encoder.encode(oauth2Properties.getClientSecret()))
//                .authorizedGrantTypes(
//                        SettingEnum.PASSWORD.value,
//                        SettingEnum.AUTHORIZATION_CODE.value,
//                        SettingEnum.REFRESH_TOKEN.value,
//                        SettingEnum.IMPLICIT.value)
//                .scopes(SettingEnum.SCOPE_READ.value,
//                        SettingEnum.SCOPE_WRITE.value,
//                        SettingEnum.SCOPE_TRUST.value);
//    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore())
                .userApprovalHandler(userApprovalHandler)
                .tokenServices(tokenServices())
                .authenticationManager(authenticationManager);
    }

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(jedisConnectionFactory);
    }

    @Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices services = new DefaultTokenServices();
        services.setTokenStore(tokenStore());
        services.setSupportRefreshToken(true);
        services.setReuseRefreshToken(false);
        services.setTokenEnhancer(jwtAccessTokenConverter());
        services.setClientDetailsService(clientDetailService);
        return services;
    }
}
