package com.Further.jdbcdao;

import com.Further.dao.ProfileDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.Further.model.Profile;

@Component
public class JdbcProfileDao implements ProfileDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcProfileDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createProfile(Profile profile) {
        String sql = "INSERT INTO profile (user_id, bio, email, first_name, last_name) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, profile.getUser_id(), profile.getBio(), profile.getEmail(), profile.getFirst_name(), profile.getLast_name());
    }

    public void updateProfile(Profile profile) {
        String sql = "UPDATE profile SET bio = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, profile.getBio(), profile.getUser_id());
    }

    public void deleteProfile(int userId) {
        String sql = "DELETE FROM profile WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
    }

    public Profile getProfileByUserId(int userId) {
        String sql = "SELECT * FROM profile WHERE user_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
        if (rowSet.next()) {
            return mapRowToProfile(rowSet);
        }
        return null;
    }

    private Profile mapRowToProfile(SqlRowSet rs) {
        Profile profile = new Profile();
        profile.setUser_id(rs.getInt("user_id"));
        profile.setBio(rs.getString("bio"));
        profile.setEmail(rs.getString("email"));
        profile.setFirst_name("first_name");
        profile.setLast_name("last_name");
        return profile;
    }
}
