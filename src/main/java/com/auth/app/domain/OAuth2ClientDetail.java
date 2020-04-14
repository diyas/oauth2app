package com.auth.app.domain;

import com.auth.app.global.AuthorizedGrantTypes;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
//@Table(name = "oauth_client_details")
public class OAuth2ClientDetail implements Serializable {

    @Id
    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Column(name = "resource_ids", nullable = false)
    private String resourceIds;

    @Column(name = "client_secret", nullable = false)
    private String clientSecret;

//    @Getter
//    @Setter
//    @Enumerated(EnumType.STRING)
//    @Column(name = "scope", nullable = false)
//    private Scope scope;

    @Enumerated(EnumType.STRING)
    @Column(name = "authorized_grant_types", nullable = false)
    private AuthorizedGrantTypes authorizedGrantTypes;

    @Column(name = "web_server_redirect_uri", nullable = false)
    private String webServerRedirectUri;

    @Column(name = "authorities", nullable = false)
    private String Authorities;

    @Column(name = "access_token_validity", nullable = false)
    private Integer accessTokenValidity;

    @Column(name = "refresh_token_validity", nullable = false)
    private Integer refreshTokenValidity;

    @Lob
    @Column(name = "additional_information", nullable = false)
    private String additionalInformation;

    @Column(name = "autoapprove", nullable = false)
    private Boolean autoApprove;
}
