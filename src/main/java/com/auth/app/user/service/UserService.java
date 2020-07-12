package com.auth.app.user.service;

import com.auth.app.repository.UserRepo;
import com.auth.app.user.model.UserRoles;
import com.auth.app.user.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("test")
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(userId);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new User(String.valueOf(user.getUsername()), user.getPassword(), getAuthority(user));
    }

    private List<SimpleGrantedAuthority> getAuthority(Users user) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (UserRoles t : user.getUserRoles())
            list.add(new SimpleGrantedAuthority(t.getRole().toString()));
        return list;
    }
}
