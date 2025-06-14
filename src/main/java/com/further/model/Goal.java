package com.further.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_id")
    private Long goalId;

    @Column(name = "user_id")
    private Long userId;

    private String goal;

    @Column(name = "goal_type")
    private String goalType;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    @Column(name = "private")
    private Boolean privateGoal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id", nullable = true)
    private Routine routine;

    public Goal() {}

    public Goal(Long goalId, Long userId, String goal, String goalType, LocalDate dateCreated, Boolean privateGoal) {
        this.goalId = goalId;
        this.userId = userId;
        this.goal = goal;
        this.goalType = goalType;
        this.dateCreated = dateCreated;
        this.privateGoal = privateGoal;
    }

    public Long getGoalId() {
        return goalId;
    }

    public void setGoalId(Long goalId) {
        this.goalId = goalId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getGoalType() {
        return goalType;
    }

    public void setGoalType(String goalType) {
        this.goalType = goalType;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Boolean getPrivateGoal() {
        return privateGoal;
    }

    public void setPrivateGoal(Boolean privateGoal) {
        this.privateGoal = privateGoal;
    }

    public Routine getRoutine() {
        return routine;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }
}
