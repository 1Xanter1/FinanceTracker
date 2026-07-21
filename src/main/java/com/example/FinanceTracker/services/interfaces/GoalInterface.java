package com.example.FinanceTracker.services.interfaces;

import com.example.FinanceTracker.entities.Goal;

import java.util.List;

public interface GoalInterface {

    Goal createGoal(Goal goal);

    Goal getGoalById(Long id);

    List<Goal> getAllGoals();

    Goal updateGoal(Long id,Goal goal);

    void deleteGoal(Long id);

    List<Goal> getGoalsByUser(Long userId);

}
