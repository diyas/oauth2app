package com.auth.app.repository;

import com.auth.app.user.model.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<Users, Long> {
    public Users findByUsername(String username);

}
