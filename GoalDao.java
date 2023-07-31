package com.Further.dao;

import com.Further.model.Goal;

import java.util.List;

public interface GoalDao {

    void createGoal(Goal goal);

    void updateGoal(Goal goal);

    void deleteGoal(int goalId);

    Goal getGoalById(int goalId);

    List<Goal> getAllGoalsByUserId(int userId);
}
