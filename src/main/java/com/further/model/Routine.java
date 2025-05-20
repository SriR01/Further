package com.further.model;

import java.sql.Timestamp;
import jakarta.persistence.*;

@Entity
@Table(name = "routine")
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id")
    private Long routineId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "routine_name")
    private String routineName;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @Column(name = "goal_1")
    private String goal1;

    @Column(name = "goal_2")
    private String goal2;

    @Column(name = "goal_3")
    private String goal3;

    @Column(name = "goal_4")
    private String goal4;

    @Column(name = "goal_5")
    private String goal5;

    public Routine(Long routineId, Long userId, String routineName, Timestamp startTime, Timestamp endTime,
                   String goal1, String goal2, String goal3, String goal4, String goal5) {
        this.routineId = routineId;
        this.userId = userId;
        this.routineName = routineName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.goal1 = goal1;
        this.goal2 = goal2;
        this.goal3 = goal3;
        this.goal4 = goal4;
        this.goal5 = goal5;
    }

    public Routine() {}

    public Long getRoutineId() {
        return routineId;
    }

    public void setRoutineId(Long routineId) {
        this.routineId = routineId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRoutineName() {
        return routineName;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getGoal1() {
        return goal1;
    }

    public void setGoal1(String goal1) {
        this.goal1 = goal1;
    }

    public String getGoal2() {
        return goal2;
    }

    public void setGoal2(String goal2) {
        this.goal2 = goal2;
    }

    public String getGoal3() {
        return goal3;
    }

    public void setGoal3(String goal3) {
        this.goal3 = goal3;
    }

    public String getGoal4() {
        return goal4;
    }

    public void setGoal4(String goal4) {
        this.goal4 = goal4;
    }

    public String getGoal5() {
        return goal5;
    }

    public void setGoal5(String goal5) {
        this.goal5 = goal5;
    }
}
