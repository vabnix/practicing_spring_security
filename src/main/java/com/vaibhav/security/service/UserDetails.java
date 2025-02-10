package com.vaibhav.security.service;

import com.vaibhav.security.entity.UserPrinciple;
import com.vaibhav.security.entity.Users;
import com.vaibhav.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users = userRepository.findByUsername(username);
        if(users == null) {
            System.out.println("User not found ");
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrinciple(users);
    }
}
