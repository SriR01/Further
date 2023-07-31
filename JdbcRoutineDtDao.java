package com.Further.jdbcdao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.Further.dao.RoutineDtDao;
import com.Further.model.Journal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.Further.model.RoutineDt;

@Component
public class JdbcRoutineDtDao implements RoutineDtDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcRoutineDtDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createRoutineDt(RoutineDt routineDt) {
        String sql = "INSERT INTO routinedt (routine_id, user_id, date_current, goal_1_flag, goal_2_flag, goal_3_flag, " +
                "goal_4_flag, goal_5_flag, goal_1_flag_time, goal_2_flag_time, goal_3_flag_time, goal_4_flag_time, " +
                "goal_5_flag_time, win_flag, win_flag_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, routineDt.getRoutine_id(), routineDt.getUser_id(), routineDt.getDate_current(),
                routineDt.getGoal_1_flag(), routineDt.getGoal_2_flag(), routineDt.getGoal_3_flag(),
                routineDt.getGoal_4_flag(), routineDt.getGoal_5_flag(), routineDt.getGoal_1_flag_time(),
                routineDt.getGoal_2_flag_time(), routineDt.getGoal_3_flag_time(), routineDt.getGoal_4_flag_time(),
                routineDt.getGoal_5_flag_time(), routineDt.getWin_flag(), routineDt.getWin_flag_time());
    }

    public void updateRoutineDt(RoutineDt routineDt) {
        String sql = "UPDATE routinedt SET user_id = ?, date_current = ?, goal_1_flag = ?, goal_2_flag = ?, " +
                "goal_3_flag = ?, goal_4_flag = ?, goal_5_flag = ?, goal_1_flag_time = ?, goal_2_flag_time = ?, " +
                "goal_3_flag_time = ?, goal_4_flag_time = ?, goal_5_flag_time = ?, win_flag = ?, win_flag_time = ? " +
                "WHERE date_current = ?";
        jdbcTemplate.update(sql, routineDt.getUser_id(), routineDt.getDate_current(), routineDt.getGoal_1_flag(),
                routineDt.getGoal_2_flag(), routineDt.getGoal_3_flag(), routineDt.getGoal_4_flag(),
                routineDt.getGoal_5_flag(), routineDt.getGoal_1_flag_time(), routineDt.getGoal_2_flag_time(),
                routineDt.getGoal_3_flag_time(), routineDt.getGoal_4_flag_time(), routineDt.getGoal_5_flag_time(),
                routineDt.getWin_flag(), routineDt.getWin_flag_time(), routineDt.getDate_current());
    }

    public void deleteRoutineDt(int routineId) {
        String sql = "DELETE FROM routinedt WHERE routine_id = ?";
        jdbcTemplate.update(sql, routineId);
    }

    public RoutineDt getRoutineDtById(int routineId) {
        String sql = "SELECT * FROM routinedt WHERE routine_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, routineId);
        if (rowSet.next()) {
            return mapRowToRoutineDt(rowSet);
        }
        return null;
    }

    public List<RoutineDt> getRoutineDtsByUserId(int userId) {
        List<RoutineDt> routineDts = new ArrayList<>();
        String sql = "SELECT * FROM routinedt WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            RoutineDt routineDt = mapRowToRoutineDt(results);
            routineDts.add(routineDt);
        }
        return routineDts;
    }

    public RoutineDt getRoutineDtByUserIdAndDateValue(int userId, String dateValue) {
        String sql = "SELECT * FROM routinedt WHERE user_id = ? AND date_current = CAST(? AS DATE)";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId, dateValue);
        if (rowSet.next()) {
            return mapRowToRoutineDt(rowSet);
        }
        return null;
    }

    private RoutineDt mapRowToRoutineDt(SqlRowSet rs) {
        RoutineDt routineDt = new RoutineDt();
        routineDt.setRoutine_id(rs.getInt("routine_id"));
        routineDt.setUser_id(rs.getInt("user_id"));
        routineDt.setDate_current(rs.getTimestamp("date_current").toLocalDateTime().toLocalDate());
        routineDt.setGoal_1_flag(rs.getBoolean("goal_1_flag"));
        routineDt.setGoal_2_flag(rs.getBoolean("goal_2_flag"));
        routineDt.setGoal_3_flag(rs.getBoolean("goal_3_flag"));
        routineDt.setGoal_4_flag(rs.getBoolean("goal_4_flag"));
        routineDt.setGoal_5_flag(rs.getBoolean("goal_5_flag"));
        routineDt.setGoal_1_flag_time(rs.getTimestamp("goal_1_flag_time"));
        routineDt.setGoal_2_flag_time(rs.getTimestamp("goal_2_flag_time"));
        routineDt.setGoal_3_flag_time(rs.getTimestamp("goal_3_flag_time"));
        routineDt.setGoal_4_flag_time(rs.getTimestamp("goal_4_flag_time"));
        routineDt.setGoal_5_flag_time(rs.getTimestamp("goal_5_flag_time"));
        routineDt.setWin_flag(rs.getBoolean("win_flag"));
        routineDt.setWin_flag_time(rs.getTimestamp("win_flag_time"));
        return routineDt;
    }
}
