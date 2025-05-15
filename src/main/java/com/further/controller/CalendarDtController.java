package com.further.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.further.repository.CalendarDtRepository;
import com.further.model.CalendarDt;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8088")
@RequestMapping("/calendardt")
public class CalendarDtController {

    private final CalendarDtRepository calendarDtRepository;

    @Autowired
    public CalendarDtController(CalendarDtRepository calendarDtRepository) {
        this.calendarDtRepository = calendarDtRepository;
    }

    @PostMapping
    public CalendarDt createCalendarDt(@RequestBody CalendarDt calendarDt) {
        return calendarDtRepository.save(calendarDt);
    }

    @DeleteMapping("/delete/{date_id}")
    public void deleteCalendarDt(@PathVariable Long date_id) {
        calendarDtRepository.deleteById(date_id);
    }

    @PutMapping("/{date_id}")
    public CalendarDt updateCalendarDt(@PathVariable Long date_id, @RequestBody CalendarDt calendarDt) {
        Optional<CalendarDt> existingOpt = calendarDtRepository.findById(date_id);
        if (existingOpt.isEmpty()) {
            throw new RuntimeException("CalendarDt not found");
        }
        CalendarDt existing = existingOpt.get();
        existing.setUser_id(calendarDt.getUser_id());
        existing.setDates(calendarDt.getDates());
        existing.setRoutine(calendarDt.getRoutine());
        // Add other fields to update as needed
        return calendarDtRepository.save(existing);
    }

    @GetMapping("/getdts/{user_id}")
    public List<CalendarDt> getAllCalendarDtFromUserId(@PathVariable Long user_id) {
        return calendarDtRepository.findByUserId(user_id);
    }

    @GetMapping("/getdt/{date_id}")
    public CalendarDt getCalendarDtFromDateId(@PathVariable Long date_id) {
        Optional<CalendarDt> result = calendarDtRepository.findById(date_id);
        return result.orElse(null);
    }
}