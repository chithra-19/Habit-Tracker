package com.habittracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habittracker.model.Habit;
import com.habittracker.model.User;
import com.habittracker.repository.HabitRepository;

@Service
public class HabitService {

    @Autowired
    private HabitRepository habitRepository;

    public Habit createHabit(Habit habit, User user) {
        habit.setUser(user);
        return habitRepository.save(habit);
    }

    public List<Habit> getHabitsForUser(User user) {
        return habitRepository.findByUser(user);
    }

    public Optional<Habit> getHabitById(Long id) {
        return habitRepository.findById(id);
    }

    public void deleteHabit(Habit habit) {
        habitRepository.delete(habit);
    }

    public Habit updateHabit(Habit habit) {
        return habitRepository.save(habit);
    }
}
