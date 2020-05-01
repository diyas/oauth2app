package com.auth.app.user.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedHashSet;

@Data
@Entity
@Table(name = "users")
public class Users {

    @Id
    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column
    private String token;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "user")
    private Collection<UserRoles> userRoles = new LinkedHashSet<>();
}
