package com.cricket.server.config;

import com.cricket.server.dao.user.UserRepo;
import com.cricket.server.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepo.findById(s)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));
        return new MyUserDetails(user);
    }
}
