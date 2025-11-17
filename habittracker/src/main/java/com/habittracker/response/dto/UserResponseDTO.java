package com.habittracker.response.dto;

public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;

    public UserResponseDTO(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    // getters only (optional)
}
