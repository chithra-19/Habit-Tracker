package com.habittracker.response.dto;

public class HabitResponseDTO {

    private Long habitId;
    private String habitName;
    private String description;

    // ✅ Constructor
    public HabitResponseDTO(Long habitId, String habitName, String description) {
        this.habitId = habitId;
        this.habitName = habitName;
        this.description = description;
    }

    // Getters & Setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
