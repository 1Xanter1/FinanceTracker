package com.example.FinanceTracker.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "goal")
public class Goal {
    @Id
    @Column(name = "goal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int goalId;

    @Column(name = "title")
    private String title;

    @Column(name = "target_amount")
    private double targetAmount;

    @Column(name = "current_amount")
    private double currentAmount;

    @Column(name = "deadline")
    private LocalDate deadline;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
