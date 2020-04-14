package com.auth.app.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
//@Table(name = "oauth_count_access")
public class OAuth2CountAccess implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "oauth_count_access")
    private String idOAuth2CountAccess;

    @Column(name = "count_access")
    private Integer countAccess;

    @Column(name = "client_id")
    private String clientId;
}
