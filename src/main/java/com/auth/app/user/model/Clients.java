
package com.auth.app.user.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "oauth2_client_details_view")
public class Clients {

    @Id
    @Column(name="client_id")
    private String clientId;
    @Column(name="resource_ids")
    private String resourceIds;
    @Column(name="client_secret")
    private String clientSecret;
    @Column
    private String scope;
    @Column(name="authorized_grant_types")
    private String authorizedGrantTypes;
    @Column(name="web_server_redirect_uri")
    private String webServerRedirectUri;
    @Column
    private String authorities;
    @Column(name="access_token_validity")
    private Integer accessTokenValidity;
    @Column(name="refresh_token_validity")
    private Integer refreshTokenValidity;
    @Column(name="additional_information")
    private String additionalInformation;
    @Column(name = "auto_approve")
    private boolean autoApprove;

}
