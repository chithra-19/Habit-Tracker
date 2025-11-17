package com.habittracker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.habittracker.model.User;

public class HabittrackerTest {

    @Test
    void testUserCreation() {
        User user = new User();
        user.setUsername("pop");
        user.setEmail("pop@example.com");
        user.setPassword("password");

        assertEquals("pop", user.getUsername());
        assertEquals("pop@example.com", user.getEmail());
    }
}
