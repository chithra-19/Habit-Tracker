package com.habittracker.request.dto;

import java.time.LocalDate;

public class HabitLogRequest {
    private Long habitId;
    private LocalDate date;
    private String status;

    public Long getHabitId() { return habitId; }
    public void setHabitId(Long habitId) { this.habitId = habitId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
