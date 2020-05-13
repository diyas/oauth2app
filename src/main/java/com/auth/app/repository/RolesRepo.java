package com.auth.app.repository;

import com.auth.app.user.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepo extends JpaRepository<Roles, Long> {
}
