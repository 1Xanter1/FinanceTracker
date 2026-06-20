package controllers;


import entities.Goal;
import org.springframework.web.bind.annotation.*;
import repositories.GoalRepository;

import java.util.List;

@RestController
@RequestMapping("/goals")
public class GoalController {

    private final GoalRepository goalRepository;

    public GoalController(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @PostMapping
    public Goal createGoal(@RequestBody Goal goal) {
        return goalRepository.save(goal);
    }

    @GetMapping
    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }

    @GetMapping("/{id}")
    public Goal getGoalById(@PathVariable Long id) {
        return goalRepository.findById(id).get();
    }

    @PostMapping("/{id}")
    public Goal updateGoal(@PathVariable Long id, @RequestBody Goal goal) {
        return goalRepository.save(goal);
    }

    @DeleteMapping("/{id}")
    public void deleteGoal(@PathVariable Long id) {
        goalRepository.deleteById(id);
    }
}
