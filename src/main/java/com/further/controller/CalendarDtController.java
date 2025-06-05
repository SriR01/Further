package com.further.controller;
import com.further.model.Routine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.further.repository.CalendarDtRepository;
import com.further.model.CalendarDt;
import com.further.dto.SetTodayRoutineRequest;
import com.further.repository.RoutineRepository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@RestController
@RequestMapping("/calendardt")
public class CalendarDtController {

    private final CalendarDtRepository calendarDtRepository;

    @Autowired
    private RoutineRepository routineRepository;

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
        existing.setUserId(calendarDt.getUserId());
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

    @PostMapping("/set-today")
    public ResponseEntity<?> setTodayRoutine(@RequestBody SetTodayRoutineRequest req) {
        CalendarDt calendarDt = calendarDtRepository
            .findByUserIdAndDates(req.getUserId(), LocalDate.parse(req.getDate()))
            .orElse(new CalendarDt());
        calendarDt.setUserId(req.getUserId());
        calendarDt.setDates(LocalDate.parse(req.getDate()));

        // Fetch the Routine entity using the routineId
        Routine routine = routineRepository.findById(req.getRoutineId())
            .orElseThrow(() -> new RuntimeException("Routine not found"));
        calendarDt.setRoutine(routine);

        calendarDtRepository.save(calendarDt);
        return ResponseEntity.ok(calendarDt);
    }
}