package com.auth.app.repository;

import com.auth.app.user.model.Clients;
import com.auth.app.user.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Clients, Long> {
    public Clients findByClientId(String clientId);

}
