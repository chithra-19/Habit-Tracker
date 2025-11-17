package com.habittracker.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.habittracker.response.dto.ChartDataResponseDTO;
import com.habittracker.response.dto.DashboardSummaryResponseDTO;
import com.habittracker.model.Habit;
import com.habittracker.model.HabitLog;
import com.habittracker.model.User;
import com.habittracker.repository.HabitLogRepository;
import com.habittracker.repository.HabitRepository;

@Service
public class DashboardService {

    private final HabitRepository habitRepository;
    private final HabitLogRepository habitLogRepository;

    public DashboardService(HabitRepository habitRepository, HabitLogRepository habitLogRepository) {
        this.habitRepository = habitRepository;
        this.habitLogRepository = habitLogRepository;
    }

    // ✅ Summary for dashboard
    public DashboardSummaryResponseDTO getSummary(Long userId, String username) {
        List<Habit> habits = habitRepository.findByUserId(userId);
        int totalHabits = habits.size();

        LocalDate today = LocalDate.now();
        int completedToday = habitLogRepository.countByHabit_User_IdAndStatusAndDate(userId, HabitLog.Status.COMPLETED, today);
        int missedToday = habitLogRepository.countByHabit_User_IdAndStatusAndDate(userId, HabitLog.Status.MISSED, today);

        int currentStreak = calculateCurrentStreak(userId);

        return new DashboardSummaryResponseDTO(username, totalHabits, completedToday, missedToday, currentStreak);
    }



    // ✅ Chart data for last 7 days
    public List<ChartDataResponseDTO> getChartData(Long userId) {
        List<ChartDataResponseDTO> chartData = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (int i = 6; i >= 0; i--) {
            LocalDate date = today.minusDays(i);
            int completed = habitLogRepository.countByUserIdAndStatusAndDate(userId, HabitLog.Status.COMPLETED, date);
            chartData.add(new ChartDataResponseDTO(date, completed));
        }

        return chartData;
    }

    // 🔹 Example streak calculation (consecutive completed habits)
    private int calculateCurrentStreak(Long userId) {
        // Simplified: Count consecutive days from today backward
        int streak = 0;
        LocalDate day = LocalDate.now();

        while (habitLogRepository.existsByUserIdAndStatusAndDate(userId, HabitLog.Status.COMPLETED, day)) {
            streak++;
            day = day.minusDays(1);
        }

        return streak;
    }
}
