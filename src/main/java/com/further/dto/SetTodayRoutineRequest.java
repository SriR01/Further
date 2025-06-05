package com.further.dto;

public class SetTodayRoutineRequest {
    private Long userId;
    private String date; // or LocalDate if you prefer
    private Long routineId;

    // Getters and setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public Long getRoutineId() { return routineId; }
    public void setRoutineId(Long routineId) { this.routineId = routineId; }
}