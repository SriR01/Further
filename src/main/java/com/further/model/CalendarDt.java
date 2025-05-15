package com.further.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "calendar_dt")
public class CalendarDt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "date_id")
    private Long id;

    private int user_id;

    private LocalDate dates;

    @ManyToOne
    @JoinColumn(name = "routine_id", referencedColumnName = "routine_id")
    private Routine routine;

    public CalendarDt() {}

    public CalendarDt(Long id, int user_id, LocalDate dates, Routine routine) {
        this.id = id;
        this.user_id = user_id;
        this.dates = dates;
        this.routine = routine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public LocalDate getDates() {
        return dates;
    }

    // Do I need this for for dates or single date?

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