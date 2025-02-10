package com.vaibhav.security.service;

import com.vaibhav.security.entity.Users;
import com.vaibhav.security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    //private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        //this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public void registerUser(Users users) {
        // Check if username already exists
        if (userRepository.findByUsername(users.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }

        // Create new user with encoded password
        Users newUser = new Users();
        newUser.setUsername(users.getUsername());
        //newUser.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        newUser.setPassword(users.getPassword());
        userRepository.save(newUser);
        log.info("User registered successfully: {}", users.getUsername());
    }

    public void updatePassword(Users users) {
        if (users == null || users.getUsername() == null || users.getPassword() == null) {
            throw new IllegalArgumentException("Invalid user data");
        }

        Users existingUser = userRepository.findByUsername(users.getUsername());
        if (existingUser == null) {
            throw new RuntimeException("User not found: " + users.getUsername());
        }

        // Update password with new encoded password
        //existingUser.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        existingUser.setPassword(users.getPassword());
        userRepository.save(existingUser);
        log.info("Password updated successfully for user: {}", users.getUsername());
    }

    // Helper method to check if a password is BCrypt encoded
    private boolean isBCryptEncoded(String password) {
        return password != null && password.startsWith("$2a$");
    }
}