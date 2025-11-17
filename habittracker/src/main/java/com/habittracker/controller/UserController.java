package com.habittracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.habittracker.request.dto.UserRequestDTO;
import com.habittracker.model.User;
import com.habittracker.security.JwtUtil;
import com.habittracker.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequestDTO dto) {
        User user = new User(dto.getUsername(), dto.getEmail(), dto.getPassword());
        userService.register(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDTO dto) {
        return userService.login(dto.getEmail(), dto.getPassword())
                .map(user -> ResponseEntity.ok(jwtUtil.generateToken(user.getUsername())))
                .orElse(ResponseEntity.status(401).body("Invalid credentials"));
    }
}
