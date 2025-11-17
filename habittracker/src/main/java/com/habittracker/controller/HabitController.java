package com.habittracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.habittracker.request.dto.HabitRequestDTO;
import com.habittracker.model.Habit;
import com.habittracker.model.User;
import com.habittracker.service.HabitService;
import com.habittracker.service.UserService;

@RestController
@RequestMapping("/api/habits")
public class HabitController {

    @Autowired
    private HabitService habitService;

    @Autowired
    private UserService userService;

    // Create a new habit
    @PostMapping("/{username}")
    public Habit createHabit(@PathVariable String username, @RequestBody HabitRequestDTO dto) {
        User user = userService.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Habit habit = new Habit(dto.getTitle(), dto.getDescription());
        return habitService.createHabit(habit, user);
    }

    // Get all habits for a user
    @GetMapping("/{username}")
    public List<Habit> getHabits(@PathVariable String username) {
        User user = userService.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return habitService.getHabitsForUser(user);
    }

    // Update habit
    @PutMapping("/{id}")
    public Habit updateHabit(@PathVariable Long id, @RequestBody HabitRequestDTO dto) {
        Habit habit = habitService.getHabitById(id).orElseThrow(() -> new RuntimeException("Habit not found"));
        habit.setTitle(dto.getTitle());
        habit.setDescription(dto.getDescription());
        return habitService.updateHabit(habit);
    }

    // Delete habit
    @DeleteMapping("/{id}")
    public String deleteHabit(@PathVariable Long id) {
        Habit habitServiceObj = habitService.getHabitById(id).orElseThrow(() -> new RuntimeException("Habit not found"));
        habitService.deleteHabit(habitServiceObj);
        return "Habit deleted";
    }
}
