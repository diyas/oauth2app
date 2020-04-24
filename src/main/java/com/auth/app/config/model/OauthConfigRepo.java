package com.auth.app.config.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthConfigRepo extends JpaRepository<OauthConfig, Long> {
    OauthConfig findByConfigName(OauthConfigEnum config);

}
