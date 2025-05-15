package com.further.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.further.repository.GoalRepository;
import com.further.model.Goal;
import java.util.List;

@RestController
@RequestMapping("/goals")
@CrossOrigin(origins = "http://localhost:8088")
public class GoalController {

    private final GoalRepository goalRepository;

    @Autowired
    public GoalController(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @PostMapping
    public Goal createGoal(@RequestBody Goal goal) {
        return goalRepository.save(goal);
    }

    @PutMapping("/{goalId}")
    public Goal updateGoal(@PathVariable Long goal_id, @RequestBody Goal goal) {
        goal.setGoal_id(goal_id);
        return goalRepository.save(goal);
    }

    @DeleteMapping("/delete/{goalId}")
    public void deleteGoal(@PathVariable int goalId) {
        goalRepository.deleteById(goalId);
    }

    @GetMapping("/{goalId}")
    public Goal getGoalById(@PathVariable int goalId) {
        return goalRepository.findById(goalId).orElse(null);
    }

    @GetMapping("/users/{userId}")
    public List<Goal> getAllGoalsByUserId(@PathVariable int userId) {
        return goalRepository.findByUserId(userId);
    }
}