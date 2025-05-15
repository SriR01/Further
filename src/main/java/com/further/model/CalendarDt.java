package com.further.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "calendar_dt")
public class CalendarDt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "date_id")
    private Long date_id;

    @Column(name = "user_id")
    private Long user_id;

    private LocalDate dates;

    @ManyToOne
    @JoinColumn(name = "routine_id", referencedColumnName = "routine_id")
    private Routine routine;

    public CalendarDt() {}

    public CalendarDt(Long date_id, Long user_id, LocalDate dates, Routine routine) {
        this.date_id = date_id;
        this.user_id = user_id;
        this.dates = dates;
        this.routine = routine;
    }

    public Long getId() {
        return date_id;
    }

    public void setId(Long date_id) {
        this.date_id = date_id;
    }

    public Long getUser_Id() {
        return user_id;
    }

    public void setUser_Id(Long user_id) {
        this.user_id = user_id;
    }

    public LocalDate getDates() {
        return dates;
    }

    public void setDates(LocalDate dates) {
        this.dates = dates;
    }

    public Routine getRoutine() {
        return routine;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }
}