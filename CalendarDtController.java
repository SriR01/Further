package com.Further.controller;
import com.Further.model.CalendarDt;
import com.Further.dao.CalendarDtDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8088")
@RequestMapping("/calendardt")
public class CalendarDtController {

    private final CalendarDtDao calendarDtService;

    @Autowired
    public CalendarDtController(CalendarDtDao calendarDtService) {
        this.calendarDtService = calendarDtService;
    }

    @PostMapping
    public void createCalendarDt(@RequestBody CalendarDt calendarDt) {
        calendarDtService.createCalendarDt(calendarDt);
    }

    @DeleteMapping
    @RequestMapping("delete/{date_id}")
    public void deleteCalendarDt(@PathVariable int date_id) {
        calendarDtService.deleteCalendarDt(date_id);
    }

    @PutMapping
    @RequestMapping("{date_id}")
    public void updateCalendarDt(@RequestBody CalendarDt calendarDt) {
        calendarDtService.updateCalendarDt(calendarDt);
    }

    @GetMapping
    @RequestMapping("/getdts/{user_id}")
    public List<CalendarDt> getAllCalendarDtFromUserId(@PathVariable int user_id) {
        return calendarDtService.getAllCalendarDtsByUserId(user_id);
    }

    @GetMapping("/getdt/{user_id}/{dateValue}")
    public CalendarDt getUserCalendarDtByDateValue(@PathVariable int user_id, @PathVariable String dateValue) {
        System.out.println("This endpoint was accessed");
        return calendarDtService.getCalendarDtByUserIdAndDateValue(user_id, dateValue);
    }

    @GetMapping
    @RequestMapping("/getdt/{date_id}")
    public CalendarDt getCalendarDtFromDateId(@PathVariable int date_id) {
        return calendarDtService.getCalendarDtById(date_id);
    }

}
