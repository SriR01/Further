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
    private Long routine_dt_id;

    @Column(name = "routine_id")
    private Long routine_id;

    @Column(name = "user_id")
    private Long user_id;

    private LocalDate date_current;
    private Boolean goal_1_flag;
    private Boolean goal_2_flag;
    private Boolean goal_3_flag;
    private Boolean goal_4_flag;
    private Boolean goal_5_flag;
    private Timestamp goal_1_flag_time;
    private Timestamp goal_2_flag_time;
    private Timestamp goal_3_flag_time;
    private Timestamp goal_4_flag_time;
    private Timestamp goal_5_flag_time;
    private Boolean win_flag;
    private Timestamp win_flag_time;

    public RoutineDt(Long routine_id, Long user_id, LocalDate date_current, Boolean goal_1_flag, Boolean goal_2_flag, Boolean goal_3_flag,
                     Boolean goal_4_flag, Boolean goal_5_flag, Timestamp goal_1_flag_time, Timestamp goal_2_flag_time,
                     Timestamp goal_3_flag_time, Timestamp goal_4_flag_time, Timestamp goal_5_flag_time,
                     Boolean win_flag, Timestamp win_flag_time) {
        this.routine_id = routine_id;
        this.user_id = user_id;
        this.date_current = date_current;
        this.goal_1_flag = goal_1_flag;
        this.goal_2_flag = goal_2_flag;
        this.goal_3_flag = goal_3_flag;
        this.goal_4_flag = goal_4_flag;
        this.goal_5_flag = goal_5_flag;
        this.goal_1_flag_time = goal_1_flag_time;
        this.goal_2_flag_time = goal_2_flag_time;
        this.goal_3_flag_time = goal_3_flag_time;
        this.goal_4_flag_time = goal_4_flag_time;
        this.goal_5_flag_time = goal_5_flag_time;
        this.win_flag = win_flag;
        this.win_flag_time = win_flag_time;
    }

    public RoutineDt() {}

    public Long getId() {
        return routine_dt_id;
    }

    public void setId(Long routine_dt_id) {
        this.routine_dt_id = routine_dt_id;
    }

    public Long getRoutine_id() {
        return routine_id;
    }

    public void setRoutine_id(Long routine_id) {
        this.routine_id = routine_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public LocalDate getDate_current() {
        return date_current;
    }

    public void setDate_current(LocalDate date_current) {
        this.date_current = date_current;
    }

    public Boolean getGoal_1_flag() {
        return goal_1_flag;
    }

    public void setGoal_1_flag(Boolean goal_1_flag) {
        this.goal_1_flag = goal_1_flag;
    }

    public Boolean getGoal_2_flag() {
        return goal_2_flag;
    }

    public void setGoal_2_flag(Boolean goal_2_flag) {
        this.goal_2_flag = goal_2_flag;
    }

    public Boolean getGoal_3_flag() {
        return goal_3_flag;
    }

    public void setGoal_3_flag(Boolean goal_3_flag) {
        this.goal_3_flag = goal_3_flag;
    }

    public Boolean getGoal_4_flag() {
        return goal_4_flag;
    }

    public void setGoal_4_flag(Boolean goal_4_flag) {
        this.goal_4_flag = goal_4_flag;
    }

    public Boolean getGoal_5_flag() {
        return goal_5_flag;
    }

    public void setGoal_5_flag(Boolean goal_5_flag) {
        this.goal_5_flag = goal_5_flag;
    }

    public Timestamp getGoal_1_flag_time() {
        return goal_1_flag_time;
    }

    public void setGoal_1_flag_time(Timestamp goal_1_flag_time) {
        this.goal_1_flag_time = goal_1_flag_time;
    }

    public Timestamp getGoal_2_flag_time() {
        return goal_2_flag_time;
    }

    public void setGoal_2_flag_time(Timestamp goal_2_flag_time) {
        this.goal_2_flag_time = goal_2_flag_time;
    }

    public Timestamp getGoal_3_flag_time() {
        return goal_3_flag_time;
    }

    public void setGoal_3_flag_time(Timestamp goal_3_flag_time) {
        this.goal_3_flag_time = goal_3_flag_time;
    }

    public Timestamp getGoal_4_flag_time() {
        return goal_4_flag_time;
    }

    public void setGoal_4_flag_time(Timestamp goal_4_flag_time) {
        this.goal_4_flag_time = goal_4_flag_time;
    }

    public Timestamp getGoal_5_flag_time() {
        return goal_5_flag_time;
    }

    public void setGoal_5_flag_time(Timestamp goal_5_flag_time) {
        this.goal_5_flag_time = goal_5_flag_time;
    }

    public Boolean getWin_flag() {
        return win_flag;
    }

    public void setWin_flag(Boolean win_flag) {
        this.win_flag = win_flag;
    }

    public Timestamp getWin_flag_time() {
        return win_flag_time;
    }

    public void setWin_flag_time(Timestamp win_flag_time) {
        this.win_flag_time = win_flag_time;
    }
}
