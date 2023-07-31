package com.Further.jdbcdao;

import com.Further.dao.JournalDao;
import com.Further.model.CalendarDt;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.Further.model.Journal;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcJournalDao implements JournalDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcJournalDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createJournal(Journal journal) {
        String sql = "INSERT INTO journal (user_id, entry_subject, entry_data, entry_date) " +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, journal.getUser_id(), journal.getEntry_subject(),
                journal.getEntry_data(), journal.getEntry_date());
    }

    public void updateJournal(Journal journal) {
        String sql = "UPDATE journal SET entry_subject = ?, entry_data = ?, entry_date = ? " +
                "WHERE entry_id = ?";
        jdbcTemplate.update(sql, journal.getEntry_subject(), journal.getEntry_data(),
                journal.getEntry_date(), journal.getEntry_id());
    }

    public void deleteJournal(int entryId) {
        String sql = "DELETE FROM journal WHERE entry_id = ?";
        jdbcTemplate.update(sql, entryId);
    }

    public Journal getJournalById(int entryId) {
        String sql = "SELECT * FROM journal WHERE entry_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, entryId);
        if (rowSet.next()) {
            return mapRowToJournal(rowSet);
        }
        return null;
    }

    public List<Journal> getAllJournalsByUserId(int userId) {
        List<Journal> journalEntries = new ArrayList<>();
        String sql = "SELECT * FROM journal WHERE user_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
        while (rowSet.next()) {
            Journal journal = mapRowToJournal(rowSet);
            journalEntries.add(journal);
        }
        return journalEntries;
    }

    public Journal getJournalByUserIdAndDateValue(int userId, String dateValue) {
        String sql = "SELECT * FROM journal WHERE user_id = ? AND entry_date = CAST(? AS DATE)";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId, dateValue);
        if (rowSet.next()) {
            return mapRowToJournal(rowSet);
        }
        return null;
    }

    private Journal mapRowToJournal(SqlRowSet rs) {
        Journal journal = new Journal();
        journal.setEntry_id(rs.getInt("entry_id"));
        journal.setUser_id(rs.getInt("user_id"));
        journal.setEntry_subject(rs.getString("entry_subject"));
        journal.setEntry_data(rs.getString("entry_data"));
        journal.setEntry_date(rs.getTimestamp("entry_date").toLocalDateTime().toLocalDate());
        return journal;
    }
}
