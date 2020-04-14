package com.auth.app.user.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String address;
    @Column
    private String avatar;
    @Column(name = "confirmation_token")
    private String confirmationToken;
    @Column
    private String email;
    @Column(name = "email_canonical")
    private String emailCanonical;
    @Column
    private String enabled;
    @Column
    private String imei;
    @Column(name = "last_login")
    private String lastLogin;
    @Column(name = "last_mobile_login")
    private String lastMobileLogin;
    @Column(name = "merchant_id")
    private String merchantId;
    @Column
    private String name;
    @Column(name = "password_requested_at")
    private String passwordRequestedAt;
    @Column
    private String roles;
    @Column(name = "salesCorporateName")
    private String salesCorporateName;
    @Column(name = "salesId")
    private String salesId;
    @Column(name = "sales_phone")
    private String salesPhone;
    @Column
    private String salt;
    @Column(name = "username_canonical")
    private String usernameCanonical;
}
