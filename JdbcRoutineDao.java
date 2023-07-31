package com.Further.jdbcdao;

import java.util.ArrayList;
import java.util.List;

import com.Further.dao.RoutineDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.Further.model.Routine;

@Component
public class JdbcRoutineDao implements RoutineDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcRoutineDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createRoutine(Routine routine) {
        String sql = "INSERT INTO routine (user_id, routine_name, start_time, end_time, goal_1, goal_2, goal_3, goal_4, goal_5) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, routine.getUser_id(), routine.getRoutine_name(), routine.getStart_time(), routine.getEnd_time(),
                routine.getGoal_1(), routine.getGoal_2(), routine.getGoal_3(), routine.getGoal_4(),
                routine.getGoal_5());
    }

    public void updateRoutine(Routine routine) {
        String sql = "UPDATE routine SET user_id = ?, start_time = ?, end_time = ?, goal_1 = ?, goal_2 = ?, " +
                "goal_3 = ?, goal_4 = ?, goal_5 = ? WHERE routine_id = ?";
        jdbcTemplate.update(sql, routine.getUser_id(), routine.getStart_time(), routine.getEnd_time(),
                routine.getGoal_1(), routine.getGoal_2(), routine.getGoal_3(), routine.getGoal_4(),
                routine.getGoal_5(), routine.getRoutine_id());
    }

    public void deleteRoutine(int routineId) {
        String sql = "DELETE FROM routine WHERE routine_id = ?";
        jdbcTemplate.update(sql, routineId);
    }

    public Routine getRoutineById(int routineId) {
        String sql = "SELECT * FROM routine WHERE routine_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, routineId);
        if (rowSet.next()) {
            return mapRowToRoutine(rowSet);
        }
        return null;
    }


    public List<Routine> getAllRoutinesByUserId(int userId) {
        List<Routine> routines = new ArrayList<>();
        String sql = "SELECT * FROM routine WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            Routine routine = mapRowToRoutine(results);
            routines.add(routine);
        }
        return routines;
    }

    private Routine mapRowToRoutine(SqlRowSet rs) {
        Routine routine = new Routine();
        routine.setRoutine_id(rs.getInt("routine_id"));
        routine.setRoutine_name(rs.getString("routine_name"));
        routine.setUser_id(rs.getInt("user_id"));
        routine.setStart_time(rs.getTimestamp("start_time"));
        routine.setEnd_time(rs.getTimestamp("end_time"));
        routine.setGoal_1(rs.getString("goal_1"));
        routine.setGoal_2(rs.getString("goal_2"));
        routine.setGoal_3(rs.getString("goal_3"));
        routine.setGoal_4(rs.getString("goal_4"));
        routine.setGoal_5(rs.getString("goal_5"));
        return routine;
    }
}
