package com.habittracker.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "habit_logs")
public class HabitLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id", nullable = false)
    private Habit habit;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status; // COMPLETED, MISSED, SKIPPED

    // ✅ Enum for habit status
    public enum Status {
        COMPLETED,
        MISSED,
        SKIPPED
    }

    // Constructors
    public HabitLog() {}

    public HabitLog(Habit habit, LocalDate date, Status status) {
        this.habit = habit;
        this.date = date;
        this.status = status;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Habit getHabit() {
        return habit;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
