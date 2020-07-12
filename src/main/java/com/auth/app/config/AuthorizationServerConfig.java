package com.auth.app.config;

import com.auth.app.global.Oauth2Properties;
import com.auth.app.global.SettingEnum;
import com.auth.app.user.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
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

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private Oauth2Properties oauth2Properties;

//    @Autowired
//    OauthConfigRepo oauthConfig;

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

//    @Autowired
//    private BCryptPasswordEncoder encoder;

//    @Autowired
//    private TokenStore tokenStore;

    @Autowired
    private UserApprovalHandler userApprovalHandler;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ClientService clientService;

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        return new JwtAccessTokenConverter();
    }

//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getProperty("spring.datasource.jdbc.driverClassName"));
//        dataSource.setUrl(env.getProperty("spring.datasource.jdbc.url"));
//        dataSource.setUsername(env.getProperty("spring.datasource.jdbc.username"));
//        dataSource.setPassword(env.getProperty("spring.datasource.jdbc.password"));
//        return dataSource;
//    }

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
        clients.withClientDetails(clientService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore())
                .userApprovalHandler(userApprovalHandler)
                .userDetailsService(userDetailsService)
                .tokenServices(tokenServices())
                .authenticationManager(authenticationManager);
//        endpoints.exceptionTranslator(exception -> {
//            if (exception instanceof OAuth2Exception) {
//                OAuth2Exception oAuth2Exception = (OAuth2Exception) exception;
//                return ResponseEntity
//                        .status(oAuth2Exception.getHttpErrorCode())
//                        .body(new CustomOauthException(oAuth2Exception.getMessage()));
//            } else {
//                throw exception;
//            }
//        });
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
        services.setAccessTokenValiditySeconds(oauth2Properties.getTokenExpired());
        services.setRefreshTokenValiditySeconds(oauth2Properties.getRefreshTokenExpired());
        return services;
    }
}
