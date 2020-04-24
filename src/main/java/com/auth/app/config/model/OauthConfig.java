package com.auth.app.config.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "oauth_config")
public class OauthConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @Enumerated(value = EnumType.STRING)
    private OauthConfigEnum configName;
    @Column
    private String value;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column
    private Date updateDate;
    @Column
    private String createdBy;
}