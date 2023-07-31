package com.Further.controller;

import com.Further.model.Goal;
import com.Further.dao.GoalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goals")
@CrossOrigin(origins = "http://localhost:8088")
public class GoalController {

    private final GoalDao goalDao;

    @Autowired
    public GoalController(GoalDao goalDao) {
        this.goalDao = goalDao;
    }

    @PostMapping
    public void createGoal(@RequestBody Goal goal) {
        goalDao.createGoal(goal);
    }

    @PutMapping("/{goalId}")
    public void updateGoal(@PathVariable int goalId, @RequestBody Goal goal) {
        goal.setGoal_id(goalId);
        goalDao.updateGoal(goal);
    }

    @DeleteMapping("/delete/{goalId}")
    public void deleteGoal(@PathVariable int goalId) {
        goalDao.deleteGoal(goalId);
    }

    @GetMapping("/{goalId}")
    public Goal getGoalById(@PathVariable int goalId) {
        return goalDao.getGoalById(goalId);
    }

    @GetMapping("/users/{userId}")
    public List<Goal> getAllGoalsByUserId(@PathVariable int userId) {
        return goalDao.getAllGoalsByUserId(userId);
    }
}
