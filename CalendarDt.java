package com.Further.model;
import java.time.LocalDate;

public class CalendarDt {

    private int date_id;
    private int user_id;
    private LocalDate dates;
    private int routine_id;

    public CalendarDt(int date_id, int user_id, LocalDate dates, int routine_id) {
        this.date_id = date_id;
        this.user_id = user_id;
        this.dates = dates;
        this.routine_id = routine_id;
    }
    public CalendarDt() {
    }

    public int getDate_id() {
        return date_id;
    }

    public void setDate_id(int date_id) {
        this.date_id = date_id;
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

    public void setDates(LocalDate dates) {
        this.dates = dates;
    }

    public int getRoutine_id() {
        return routine_id;
    }

    public void setRoutine_id(int routine_id) {
        this.routine_id = routine_id;
    }

}
