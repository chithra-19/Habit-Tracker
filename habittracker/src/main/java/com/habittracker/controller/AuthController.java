package com.habittracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.habittracker.model.User;
import com.habittracker.request.dto.UserRequestDTO;
import com.habittracker.security.JwtUtil;
import com.habittracker.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ Register new user
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequestDTO userDTO) {
        try {
            User user = authService.registerUser(
                    userDTO.getUsername(),
                    userDTO.getEmail(),
                    userDTO.getPassword() // hash in AuthService for production
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // ✅ Login user
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDTO userDTO) {
        try {
            // 1️⃣ Authenticate user
            User user = authService.login(userDTO.getUsername(), userDTO.getPassword());

            // 2️⃣ Generate JWT token
            String token = jwtUtil.generateToken(user.getUsername());

            // 3️⃣ Return token
            return ResponseEntity.ok(new JwtResponse(token));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    // Inner class to send JWT response
    public static class JwtResponse {
        private String token;

        public JwtResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
