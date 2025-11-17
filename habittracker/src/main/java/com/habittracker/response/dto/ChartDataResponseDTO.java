package com.habittracker.response.dto;

import java.time.LocalDate;

public class ChartDataResponseDTO {

    private LocalDate date;
    private int completedCount;

    public ChartDataResponseDTO(LocalDate date, int completedCount) {
        this.date = date;
        this.completedCount = completedCount;
    }

    // Getters and setters
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public int getCompletedCount() { return completedCount; }
    public void setCompletedCount(int completedCount) { this.completedCount = completedCount; }
}
