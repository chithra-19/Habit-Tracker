package com.habittracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habittracker.model.Habit;
import com.habittracker.model.User;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {

    // ✅ Find all habits for a specific user
    List<Habit> findByUser(User user);

    // Already needed for controller
    Optional<Habit> findByIdAndUserId(Long habitId, Long userId);

	List<Habit> findByUserId(Long userId);
}
