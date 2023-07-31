package com.Further.dao;

import com.Further.model.CalendarDt;

import java.util.List;

public interface CalendarDtDao {

    void createCalendarDt(CalendarDt calendarDt);

    void updateCalendarDt(CalendarDt calendarDt);

    void deleteCalendarDt(int dateId);

    CalendarDt getCalendarDtById(int dateId);

    public CalendarDt getCalendarDtByUserIdAndDateValue(int userId, String dateValue);
    List<CalendarDt> getAllCalendarDtsByUserId(int userId);
}
