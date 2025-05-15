package com.further.model;
import java.time.LocalDate;
import jakarta.persistence.*;


@Entity
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_id")
    private Long goal_id;

    private Long user_id;
    private String goal;
    private String goal_type;
    private LocalDate date_created;

    public Goal(Long goal_id, Long user_id, String goal, String goal_type, LocalDate date_created) {
        this.goal_id = goal_id;
        this.user_id = user_id;
        this.goal = goal;
        this.goal_type = goal_type;
        this.date_created = date_created;
    }
    
    public Goal() {
    }

    public Long getGoal_Id() {
        return goal_id;
    }

    public void setId(Long goal_id) {
        this.goal_id = goal_id;
    }

    public Long getUser_id() {
        return user_id;
    }
    public void setUser_id(Long user_id) {
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
