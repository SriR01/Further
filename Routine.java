package com.Further.model;

import java.sql.Timestamp;

public class Routine {


    private int routine_id;
    private int user_id;
    private String routine_name;
    private Timestamp start_time;
    private Timestamp end_time;
    private String goal_1;
    private String goal_2;
    private String goal_3;
    private String goal_4;
    private String goal_5;

    public Routine(int routine_id, int user_id, String routine_name, Timestamp start_time, Timestamp end_time, String goal_1, String goal_2,
                   String goal_3, String goal_4, String goal_5) {
        this.routine_id = routine_id;
        this.user_id = user_id;
        this.routine_name = routine_name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.goal_1 = goal_1;
        this.goal_2 = goal_2;
        this.goal_3 = goal_3;
        this.goal_4 = goal_4;
        this.goal_5 = goal_5;
    }
    public Routine() {
    }

    public int getRoutine_id() {
        return routine_id;
    }

    public void setRoutine_id(int routine_id) {
        this.routine_id = routine_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getRoutine_name() {
        return routine_name;
    }

    public void setRoutine_name(String routine_name) {
        this.routine_name = routine_name;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    public String getGoal_1() {
        return goal_1;
    }

    public void setGoal_1(String goal_1) {
        this.goal_1 = goal_1;
    }

    public String getGoal_2() {
        return goal_2;
    }

    public void setGoal_2(String goal_2) {
        this.goal_2 = goal_2;
    }

    public String getGoal_3() {
        return goal_3;
    }

    public void setGoal_3(String goal_3) {
        this.goal_3 = goal_3;
    }

    public String getGoal_4() {
        return goal_4;
    }

    public void setGoal_4(String goal_4) {
        this.goal_4 = goal_4;
    }

    public String getGoal_5() {
        return goal_5;
    }

    public void setGoal_5(String goal_5) {
        this.goal_5 = goal_5;
    }

}
