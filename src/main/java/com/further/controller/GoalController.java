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

    @PutMapping("/{goal_id}")
    public Goal updateGoal(@PathVariable Long goal_id, @RequestBody Goal goal) {
        goal.setId(goal_id);
        return goalRepository.save(goal);
    }

    @DeleteMapping("/delete/{goal_id}")
    public void deleteGoal(@PathVariable Long goal_id) {
        goalRepository.deleteById(goal_id);
    }

    @GetMapping("/{goal_id}")
    public Goal getGoalById(@PathVariable Long goal_id) {
        return goalRepository.findById(goal_id).orElse(null);
    }

    @GetMapping("/users/{user_id}")
    public List<Goal> getAllGoalsByUserId(@PathVariable Long user_id) {
        return goalRepository.findByUser_Id(user_id);
    }
}