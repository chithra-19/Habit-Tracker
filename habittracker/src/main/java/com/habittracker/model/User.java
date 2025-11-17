package com.habittracker.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    // Streak tracking
    @Column(name = "current_streak")
    private Integer currentStreak = 0;

    @Column(name = "best_streak")
    private Integer bestStreak = 0;

    @Column(name = "last_active_date")
    private LocalDate lastActiveDate;

    // Timestamps
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Relationships
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Habit> habits = new HashSet<>();

    // Constructors
    public User() {}

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Utility methods
    public void addHabit(Habit habit) {
        habits.add(habit);
        habit.setUser(this);
    }

    public void removeHabit(Habit habit) {
        habits.remove(habit);
        habit.setUser(null);
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Integer getCurrentStreak() { return currentStreak; }
    public void setCurrentStreak(Integer currentStreak) { this.currentStreak = currentStreak; }

    public Integer getBestStreak() { return bestStreak; }
    public void setBestStreak(Integer bestStreak) { this.bestStreak = bestStreak; }

    public LocalDate getLastActiveDate() { return lastActiveDate; }
    public void setLastActiveDate(LocalDate lastActiveDate) { this.lastActiveDate = lastActiveDate; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Set<Habit> getHabits() { return habits; }
    public void setHabits(Set<Habit> habits) { this.habits = habits; }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
               Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    // toString
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", currentStreak=" + currentStreak +
                ", bestStreak=" + bestStreak +
                '}';
    }
}
