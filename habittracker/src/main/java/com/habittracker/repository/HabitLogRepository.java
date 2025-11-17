package com.habittracker.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habittracker.model.HabitLog;
import com.habittracker.model.HabitLog.Status;

@Repository
public interface HabitLogRepository extends JpaRepository<HabitLog, Long> {
    List<HabitLog> findByHabitId(Long habitId);

	boolean existsByUserIdAndStatusAndDate(Long userId, Status completed, LocalDate day);

	int countByUserIdAndStatusAndDate(Long userId, Status completed, LocalDate date);

	int countByHabit_User_IdAndStatusAndDate(Long userId, Status completed, LocalDate today);
}
