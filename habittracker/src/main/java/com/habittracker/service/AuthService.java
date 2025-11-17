package com.habittracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.habittracker.model.User;
import com.habittracker.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    // Register a new user
    public User registerUser(String username, String email, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already taken");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        // Hash password before saving
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }

    // Login method
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Verify password
        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }

    // Fetch user by username for JwtAuthFilter
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
