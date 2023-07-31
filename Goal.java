package com.Further.model;
import java.time.LocalDate;

public class Goal {

    private int goal_id;
    private int user_id;
    private String goal;
    private String goal_type;
    private LocalDate date_created;

    public Goal(int goal_id, int user_id, String goal, String goal_type, LocalDate date_created) {
        this.goal_id = goal_id;
        this.user_id = user_id;
        this.goal = goal;
        this.goal_type = goal_type;
        this.date_created = date_created;
    }
    public Goal() {
    }
    public int getGoal_id() {
        return goal_id;
    }

    public void setGoal_id(int goal_id) {
        this.goal_id = goal_id;
    }

    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getGoal_type() {
        return goal_type;
    }

    public void setGoal_type(String goal_type) {
        this.goal_type = goal_type;
    }

    public LocalDate getDate_created() {
        return date_created;
    }

    public void setDate_created(LocalDate date_created) {
        this.date_created = date_created;
    }

}
