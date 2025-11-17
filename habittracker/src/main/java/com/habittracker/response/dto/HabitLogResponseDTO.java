package com.habittracker.response.dto;

import java.time.LocalDate;

public class HabitLogResponseDTO {

    private Long habitLogId;
    private Long habitId;
    private String habitName;
    private String status;
    private LocalDate date;

    // ✅ Constructor matching the controller call
    public HabitLogResponseDTO(Long habitLogId, Long habitId, String habitName, String status, LocalDate date) {
        this.habitLogId = habitLogId;
        this.habitId = habitId;
        this.habitName = habitName;
        this.status = status;
        this.date = date;
    }

    // Getters and setters
    public Long getHabitLogId() {
        return habitLogId;
    }

    public void setHabitLogId(Long habitLogId) {
        this.habitLogId = habitLogId;
    }

    public Long getHabitId() {
        return habitId;
    }

    public void setHabitId(Long habitId) {
        this.habitId = habitId;
    }

    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
