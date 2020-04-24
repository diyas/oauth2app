package com.auth.app.repository;

import com.auth.app.user.model.UserMobile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMobileRepo extends CrudRepository<UserMobile, Long> {
    public UserMobile findByUsername(String username);
}
