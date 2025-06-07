package com.further.controller;

import com.further.model.CalendarDt;
import com.further.model.RoutineDt;
import com.further.repository.CalendarDtRepository;
import com.further.repository.RoutineDtRepository;
import com.further.dto.SetTodayRoutineRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/calendardt")
public class CalendarDtController {

    private final CalendarDtRepository calendarDtRepository;
    private final RoutineDtRepository routineDtRepository;

    @Autowired
    public CalendarDtController(CalendarDtRepository calendarDtRepository, RoutineDtRepository routineDtRepository) {
        this.calendarDtRepository = calendarDtRepository;
        this.routineDtRepository = routineDtRepository;
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
        existing.setRoutineDt(calendarDt.getRoutineDt());
        // Add other fields to update as needed
        return calendarDtRepository.save(existing);
    }

    @GetMapping("/getdts/{user_id}")
    public List<CalendarDt> getAllCalendarDtFromUserId(@PathVariable Long user_id) {
        return calendarDtRepository.findByUserId(user_id);
    }

    @GetMapping("/getdt/{date_id}")
    public CalendarDt getCalendarDtFromDateId(@PathVariable Long date_id) {
        return calendarDtRepository.findById(date_id).orElse(null);
    }

    @PostMapping("/set-today")
    public ResponseEntity<?> setTodayRoutine(@RequestBody SetTodayRoutineRequest req) {
        LocalDate date = req.getDate();

        // Find the most recent RoutineDt for this user, date, and routineId
        RoutineDt routineDt = routineDtRepository.findByUserId(req.getUserId()).stream()
            .filter(dt -> date.equals(dt.getDateCurrent()) && dt.getRoutineId().equals(req.getRoutineId()))
            .max((a, b) -> Long.compare(a.getRoutineDtId(), b.getRoutineDtId()))
            .orElse(null);

        if (routineDt == null) {
            routineDt = new RoutineDt();
            routineDt.setUserId(req.getUserId());
            routineDt.setDateCurrent(date);
            routineDt.setRoutineId(req.getRoutineId());
            // Optionally set other fields from req if needed (startTime, endTime, etc.)
            routineDtRepository.save(routineDt);
        }

        // Find or create CalendarDt for this user and date
        CalendarDt calendarDt = calendarDtRepository
            .findByUserIdAndDates(req.getUserId(), date)
            .orElse(new CalendarDt());

        calendarDt.setUserId(req.getUserId());
        calendarDt.setDates(date);
        calendarDt.setRoutineDt(routineDt); // <-- associate with RoutineDt

        calendarDtRepository.save(calendarDt);
        return ResponseEntity.ok(calendarDt);
    }

    @PutMapping("/reset-routinedt")
    public ResponseEntity<?> resetRoutineDt(@RequestParam Long userId, @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        CalendarDt calendarDt = calendarDtRepository.findByUserIdAndDates(userId, localDate)
            .orElse(null);
        if (calendarDt == null) {
            return ResponseEntity.notFound().build();
        }
        calendarDt.setRoutineDt(null);
        calendarDtRepository.save(calendarDt);
        return ResponseEntity.ok(calendarDt);
    }
}