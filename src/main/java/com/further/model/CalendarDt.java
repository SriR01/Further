package com.further.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "calendar_dt")
public class
CalendarDt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "date_id")
    private Long dateId;

    @Column(name = "user_id")
    private Long userId;

    private LocalDate dates;

    @ManyToOne
    private RoutineDt routineDt;

    public CalendarDt() {}

    public CalendarDt(Long dateId, Long userId, LocalDate dates, RoutineDt routineDt) {
        this.dateId = dateId;
        this.userId = userId;
        this.dates = dates;
        this.routineDt = routineDt;
    }

    public Long getDateId() {
        return dateId;
    }

    public void setDateId(Long dateId) {
        this.dateId = dateId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDates() {
        return dates;
    }

    public void setDates(LocalDate dates) {
        this.dates = dates;
    }

    public RoutineDt getRoutineDt() {
        return routineDt;
    }

    public void setRoutineDt(RoutineDt routineDt) {
        this.routineDt = routineDt;
    }
}