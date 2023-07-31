package com.Further.jdbcdao;

import com.Further.dao.CalendarDtDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.Further.model.CalendarDt;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCalendarDtDao implements CalendarDtDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCalendarDtDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createCalendarDt(CalendarDt calendarDt) {
        String sql = "INSERT INTO calendardt (user_id, dates, routine_id) " +
                "VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, calendarDt.getUser_id(), calendarDt.getDates(), calendarDt.getRoutine_id());
    }

    public void updateCalendarDt(CalendarDt calendarDt) {
        String sql = "UPDATE calendardt SET user_id = ?, dates = ?, routine_id = ? " +
                "WHERE date_id = ?";
        jdbcTemplate.update(sql, calendarDt.getUser_id(), calendarDt.getDates(),
                calendarDt.getRoutine_id(), calendarDt.getDate_id());
    }

    public void deleteCalendarDt(int dateId) {
        String sql = "DELETE FROM calendardt WHERE date_id = ?";
        jdbcTemplate.update(sql, dateId);
    }

    public CalendarDt getCalendarDtById(int dateId) {
        String sql = "SELECT * FROM calendardt WHERE date_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, dateId);
        if (rowSet.next()) {
            return mapRowToCalendarDt(rowSet);
        }
        return null;
    }

    public List<CalendarDt> getAllCalendarDtsByUserId(int userId) {
        List<CalendarDt> calendarDts = new ArrayList<>();
        String sql = "SELECT * FROM calendardt WHERE user_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
        while (rowSet.next()) {
            CalendarDt calendarDt = mapRowToCalendarDt(rowSet);
            calendarDts.add(calendarDt);
        }
        return calendarDts;
    }

    public CalendarDt getCalendarDtByUserIdAndDateValue(int userId, String dateValue) {
        String sql = "SELECT * FROM calendardt WHERE user_id = ? AND dates = CAST(? AS DATE)";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId, dateValue);
        if (rowSet.next()) {
            return mapRowToCalendarDt(rowSet);
        }
        return null;
    }

    private CalendarDt mapRowToCalendarDt(SqlRowSet rs) {
        CalendarDt calendarDt = new CalendarDt();
        calendarDt.setDate_id(rs.getInt("date_id"));
        calendarDt.setUser_id(rs.getInt("user_id"));
        calendarDt.setDates(rs.getTimestamp("dates").toLocalDateTime().toLocalDate());
        calendarDt.setRoutine_id(rs.getInt("routine_id"));
        return calendarDt;
    }
}
