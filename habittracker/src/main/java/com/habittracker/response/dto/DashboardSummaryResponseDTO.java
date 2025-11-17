package com.habittracker.response.dto;

public class DashboardSummaryResponseDTO {

    
	private String username;
    private int totalHabits;
    private int completedToday;
    private int missedToday;
    private int currentStreak;

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getTotalHabits() {
		return totalHabits;
	}

	public void setTotalHabits(int totalHabits) {
		this.totalHabits = totalHabits;
	}

	public int getCompletedToday() {
		return completedToday;
	}

	public void setCompletedToday(int completedToday) {
		this.completedToday = completedToday;
	}

	public int getMissedToday() {
		return missedToday;
	}

	public void setMissedToday(int missedToday) {
		this.missedToday = missedToday;
	}

	public int getCurrentStreak() {
		return currentStreak;
	}

	public void setCurrentStreak(int currentStreak) {
		this.currentStreak = currentStreak;
	}

    
    // ✅ Matching constructor
    public DashboardSummaryResponseDTO(String username, int totalHabits, int completedToday, int missedToday, int currentStreak) {
        this.username = username;
        this.totalHabits = totalHabits;
        this.completedToday = completedToday;
        this.missedToday = missedToday;
        this.currentStreak = currentStreak;
    }

    // Getters and setters...
}
