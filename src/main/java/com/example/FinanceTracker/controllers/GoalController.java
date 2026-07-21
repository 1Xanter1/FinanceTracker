package com.example.FinanceTracker.controllers;


import com.example.FinanceTracker.entities.Goal;
import com.example.FinanceTracker.services.implementation.GoalService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
@CrossOrigin(
        origins = "http://127.0.0.1:5500",
        methods = {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.OPTIONS
        }
)
@RestController
@RequestMapping("/goals")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping
    public Goal createGoal(@RequestBody Goal goal) {
        return goalService.createGoal(goal);
    }

    @GetMapping
    public List<Goal> getAllGoals() {
        return goalService.getAllGoals();
    }

    @GetMapping("/{id}")
    public Goal getGoalById(@PathVariable Long id) {
        return goalService.getGoalById(id);
    }

    @PutMapping("/{id}")
    public Goal updateGoal(
            @PathVariable Long id,
            @RequestBody Goal goal) {

        return goalService.updateGoal(id, goal);
    }
    @PutMapping("/{id}/add")
    public Goal addMoney(
            @PathVariable Long id,
            @RequestParam double amount){

        return goalService.addMoney(id, amount);
    }
    @GetMapping("/user/{userId}")
    public List<Goal> getGoalsByUser(@PathVariable Long userId) {
        return goalService.getGoalsByUser(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
    }
}
