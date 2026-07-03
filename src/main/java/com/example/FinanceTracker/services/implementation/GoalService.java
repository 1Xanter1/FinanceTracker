package com.example.FinanceTracker.services.implementation;

import com.example.FinanceTracker.entities.Goal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.FinanceTracker.repositories.GoalRepository;
import com.example.FinanceTracker.services.interfaces.GoalInterface;

import java.util.List;

@Service
public class GoalService implements GoalInterface {
    private final GoalRepository goalRepository;

    @Autowired
    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @Override
    public Goal createGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    @Override
    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }

    @Override
    public Goal getGoalById(Long id) {
        return goalRepository.findById(id).orElseThrow(() -> new RuntimeException("Goal not found"));
    }

    @Override
    public Goal updateGoal(Long id, Goal updatedGoal) {
        Goal goal = getGoalById(id);

        goal.setTitle(updatedGoal.getTitle());
        goal.setDeadline(updatedGoal.getDeadline());
        goal.setTargetAmount(updatedGoal.getTargetAmount());
        goal.setCurrentAmount(updatedGoal.getCurrentAmount());

        return goalRepository.save(goal);
    }

    @Override
    public void deleteGoal(Long id) {
       goalRepository.deleteById(id);
    }

}
