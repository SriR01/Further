package com.further.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "routine_dt")
public class RoutineDt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_dt_id")
    private Long routineDtId;

    @Column(name = "routine_id")
    private Long routineId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "date_current")
    private LocalDate dateCurrent;

    @Column(name = "goal_1_flag")
    private Boolean goal1Flag;

    @Column(name = "goal_2_flag")
    private Boolean goal2Flag;

    @Column(name = "goal_3_flag")
    private Boolean goal3Flag;

    @Column(name = "goal_4_flag")
    private Boolean goal4Flag;

    @Column(name = "goal_5_flag")
    private Boolean goal5Flag;

    @Column(name = "goal_1_flag_time")
    private Timestamp goal1FlagTime;

    @Column(name = "goal_2_flag_time")
    private Timestamp goal2FlagTime;

    @Column(name = "goal_3_flag_time")
    private Timestamp goal3FlagTime;

    @Column(name = "goal_4_flag_time")
    private Timestamp goal4FlagTime;

    @Column(name = "goal_5_flag_time")
    private Timestamp goal5FlagTime;

    @Column(name = "win_flag")
    private Boolean winFlag;

    @Column(name = "win_flag_time")
    private Timestamp winFlagTime;

    public RoutineDt(Long routineId, Long userId, LocalDate dateCurrent, Boolean goal1Flag, Boolean goal2Flag, Boolean goal3Flag,
                     Boolean goal4Flag, Boolean goal5Flag, Timestamp goal1FlagTime, Timestamp goal2FlagTime,
                     Timestamp goal3FlagTime, Timestamp goal4FlagTime, Timestamp goal5FlagTime,
                     Boolean winFlag, Timestamp winFlagTime) {
        this.routineId = routineId;
        this.userId = userId;
        this.dateCurrent = dateCurrent;
        this.goal1Flag = goal1Flag;
        this.goal2Flag = goal2Flag;
        this.goal3Flag = goal3Flag;
        this.goal4Flag = goal4Flag;
        this.goal5Flag = goal5Flag;
        this.goal1FlagTime = goal1FlagTime;
        this.goal2FlagTime = goal2FlagTime;
        this.goal3FlagTime = goal3FlagTime;
        this.goal4FlagTime = goal4FlagTime;
        this.goal5FlagTime = goal5FlagTime;
        this.winFlag = winFlag;
        this.winFlagTime = winFlagTime;
    }

    public RoutineDt() {}

    public Long getRoutineDtId() {
        return routineDtId;
    }

    public void setRoutineDtId(Long routineDtId) {
        this.routineDtId = routineDtId;
    }

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

    public LocalDate getDateCurrent() {
        return dateCurrent;
    }

    public void setDateCurrent(LocalDate dateCurrent) {
        this.dateCurrent = dateCurrent;
    }

    public Boolean getGoal1Flag() {
        return goal1Flag;
    }

    public void setGoal1Flag(Boolean goal1Flag) {
        this.goal1Flag = goal1Flag;
    }

    public Boolean getGoal2Flag() {
        return goal2Flag;
    }

    public void setGoal2Flag(Boolean goal2Flag) {
        this.goal2Flag = goal2Flag;
    }

    public Boolean getGoal3Flag() {
        return goal3Flag;
    }

    public void setGoal3Flag(Boolean goal3Flag) {
        this.goal3Flag = goal3Flag;
    }

    public Boolean getGoal4Flag() {
        return goal4Flag;
    }

    public void setGoal4Flag(Boolean goal4Flag) {
        this.goal4Flag = goal4Flag;
    }

    public Boolean getGoal5Flag() {
        return goal5Flag;
    }

    public void setGoal5Flag(Boolean goal5Flag) {
        this.goal5Flag = goal5Flag;
    }

    public Timestamp getGoal1FlagTime() {
        return goal1FlagTime;
    }

    public void setGoal1FlagTime(Timestamp goal1FlagTime) {
        this.goal1FlagTime = goal1FlagTime;
    }

    public Timestamp getGoal2FlagTime() {
        return goal2FlagTime;
    }

    public void setGoal2FlagTime(Timestamp goal2FlagTime) {
        this.goal2FlagTime = goal2FlagTime;
    }

    public Timestamp getGoal3FlagTime() {
        return goal3FlagTime;
    }

    public void setGoal3FlagTime(Timestamp goal3FlagTime) {
        this.goal3FlagTime = goal3FlagTime;
    }

    public Timestamp getGoal4FlagTime() {
        return goal4FlagTime;
    }

    public void setGoal4FlagTime(Timestamp goal4FlagTime) {
        this.goal4FlagTime = goal4FlagTime;
    }

    public Timestamp getGoal5FlagTime() {
        return goal5FlagTime;
    }

    public void setGoal5FlagTime(Timestamp goal5FlagTime) {
        this.goal5FlagTime = goal5FlagTime;
    }

    public Boolean getWinFlag() {
        return winFlag;
    }

    public void setWinFlag(Boolean winFlag) {
        this.winFlag = winFlag;
    }

    public Timestamp getWinFlagTime() {
        return winFlagTime;
    }

    public void setWinFlagTime(Timestamp winFlagTime) {
        this.winFlagTime = winFlagTime;
    }
}
