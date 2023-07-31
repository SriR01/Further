package com.Further.jdbcdao;

import com.Further.dao.GoalDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.Further.model.Goal;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGoalDao implements GoalDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcGoalDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createGoal(Goal goal) {
        String sql = "INSERT INTO goal (user_id, goal, goal_type, date_created) " +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, goal.getUser_id(), goal.getGoal(), goal.getGoal_type(),
               goal.getDate_created());
    }

    public void updateGoal(Goal goal) {
        String sql = "UPDATE goal SET goal = ?, goal_type = ?, date_created = ? " +
                "WHERE goal_id = ?";
        jdbcTemplate.update(sql, goal.getGoal(), goal.getGoal_type(),
                goal.getDate_created(), goal.getGoal_id());
    }

    public void deleteGoal(int goalId) {
        String sql = "DELETE FROM goal WHERE goal_id = ?";
        jdbcTemplate.update(sql, goalId);
    }

    public Goal getGoalById(int goalId) {
        String sql = "SELECT * FROM goal WHERE goal_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, goalId);
        if (rowSet.next()) {
            return mapRowToGoal(rowSet);
        }
        return null;
    }

    public List<Goal> getAllGoalsByUserId(int userId) {
        List<Goal> goals = new ArrayList<>();
        String sql = "SELECT * FROM goal WHERE user_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
        while (rowSet.next()) {
            Goal goal = mapRowToGoal(rowSet);
            goals.add(goal);
        }
        return goals;
    }

    private Goal mapRowToGoal(SqlRowSet rs) {
        Goal goal = new Goal();
        goal.setGoal_id(rs.getInt("goal_id"));
        goal.setUser_id(rs.getInt("user_id"));
        goal.setGoal(rs.getString("goal"));
        goal.setGoal_type(rs.getString("goal_type"));
        goal.setDate_created(rs.getTimestamp("date_created").toLocalDateTime().toLocalDate());
        return goal;
    }
}
