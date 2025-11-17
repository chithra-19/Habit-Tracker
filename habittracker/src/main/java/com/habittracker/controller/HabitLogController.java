package com.habittracker.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.habittracker.model.Habit;
import com.habittracker.model.HabitLog;
import com.habittracker.model.User;
import com.habittracker.repository.HabitLogRepository;
import com.habittracker.repository.HabitRepository;
import com.habittracker.request.dto.HabitLogRequest;
import com.habittracker.response.dto.HabitLogResponseDTO;
import com.habittracker.service.UserService;

@RestController
@RequestMapping("/habits/log")
public class HabitLogController {

    @Autowired
    private HabitLogRepository habitLogRepository;

    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private UserService userService;

    // ✅ Log a habit
    @PostMapping
    public ResponseEntity<HabitLogResponseDTO> logHabit(
            @RequestBody HabitLogRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {

        // 1️⃣ Get user from JWT
        User user = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // 2️⃣ Get habit and check ownership
        Habit habit = habitRepository.findByIdAndUserId(request.getHabitId(), user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Habit not found"));

        // 3️⃣ Create HabitLog and handle enum safely
        LocalDate logDate = request.getDate() != null ? request.getDate() : LocalDate.now();
        if (logDate.isAfter(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot log future date");
        }

        HabitLog.Status status;
        try {
            status = HabitLog.Status.valueOf(request.getStatus().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid status: " + request.getStatus());
        }

        HabitLog log = new HabitLog(habit, logDate, status);
        habitLogRepository.save(log);

        // 4️⃣ Build structured response
        HabitLogResponseDTO response = new HabitLogResponseDTO(
                log.getId(),
                habit.getId(),
                habit.getTitle(),
                log.getStatus().name(),
                log.getDate()
        );

        return ResponseEntity.ok(response);
    }
}
