package com.auth.app.service;

import com.auth.app.repository.UserMobileRepo;
import com.auth.app.user.model.UserAuth;
import com.auth.app.user.model.UserMobile;
import com.auth.app.user.model.UserRoles;
import com.auth.app.user.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service(value = "userService")
public class UserService implements UserDetailsService {

    @Autowired
    UserMobileRepo userRepo;

    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserMobile user = userRepo.findByUsername(userId);
        UserAuth userAuth = new UserAuth();
        userAuth.setUsername(user.getUsername());
        userAuth.setPassword(user.getPassword());
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new User(String.valueOf(userAuth.getUsername()), userAuth.getPassword(), getAuthority(null));
    }

    private List<SimpleGrantedAuthority> getAuthority(Users user) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (UserRoles t : user.getUserRoles())
            list.add(new SimpleGrantedAuthority(t.getRole().toString()));
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
