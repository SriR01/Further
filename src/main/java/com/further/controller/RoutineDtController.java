package com.further.controller;
    
import com.further.dto.SetTodayRoutineRequest;
import com.further.model.Routine;
import com.further.model.RoutineDt;
import com.further.model.CalendarDt;
import com.further.repository.RoutineDtRepository;
import com.further.repository.RoutineRepository;
import com.further.repository.CalendarDtRepository;
import com.further.service.RoutineOptimizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/routine-dt")
public class RoutineDtController {

    private final RoutineDtRepository routineDtRepository;
    private final RoutineRepository routineRepository;
    private final CalendarDtRepository calendarDtRepository;

    @Autowired
    public RoutineDtController(
            RoutineDtRepository routineDtRepository,
            RoutineRepository routineRepository,
            CalendarDtRepository calendarDtRepository
    ) {
        this.routineDtRepository = routineDtRepository;
        this.routineRepository = routineRepository;
        this.calendarDtRepository = calendarDtRepository;
    }

    @Autowired
    private RoutineOptimizationService routineOptimizationService;

    // Updated: Accept and return List<Long> (routine IDs) for optimization
    @PostMapping("/optimize-goal-order")
    public List<Long> optimizeGoalOrder(
            @RequestParam Long userId,
            @RequestBody List<Long> todayRoutineIds
    ) {
        return routineOptimizationService.reorderRoutinesBySpeed(todayRoutineIds, userId);
    }

    @PostMapping
    public RoutineDt createRoutineDt(@RequestBody RoutineDt routineDt) {
        return routineDtRepository.save(routineDt);
    }

    @PutMapping("/{routine_dt_id}")
    public RoutineDt updateRoutineDt(@PathVariable Long routine_dt_id, @RequestBody RoutineDt routineDt) {
        routineDt.setRoutineId(routine_dt_id);
        return routineDtRepository.save(routineDt);
    }

    @DeleteMapping("/delete/{routine_dt_id}")
    public void deleteRoutineDt(@PathVariable("routine_dt_id") Long routine_dt_id) {
        routineDtRepository.deleteById(routine_dt_id);
    }

    @GetMapping("/user/{user_id}")
    public List<RoutineDt> getRoutineDtsByUserId(@PathVariable Long user_id) {
        return routineDtRepository.findByUserId(user_id);
    }

    @GetMapping("/{routine_dt_id}")
    public RoutineDt getRoutineDtById(@PathVariable Long routine_dt_id) {
        return routineDtRepository.findById(routine_dt_id).orElse(null);
    }

    @PostMapping("/set-today")
    public ResponseEntity<?> setTodayRoutineDt(@RequestBody SetTodayRoutineRequest req) {
        LocalDate date = req.getDate();
        Routine routine = routineRepository.findById(req.getRoutineId())
            .orElseThrow(() -> new RuntimeException("Routine not found"));

        // Find all RoutineDt for this user and date, pick the latest by ID
        List<RoutineDt> routineDts = routineDtRepository.findByUserId(req.getUserId());
        RoutineDt routineDt = routineDts.stream()
            .filter(dt -> date.equals(dt.getDateCurrent()))
            .max(Comparator.comparingLong(RoutineDt::getRoutineDtId))
            .orElse(null);

        if (routineDt == null) {
            // Create new RoutineDt
            routineDt = new RoutineDt();
            routineDt.setUserId(req.getUserId());
            routineDt.setDateCurrent(date);
            routineDt.setRoutineId(req.getRoutineId());
            routineDt.setGoal1(routine.getGoal1());
            routineDt.setGoal2(routine.getGoal2());
            routineDt.setGoal3(routine.getGoal3());
            routineDt.setGoal4(routine.getGoal4());
            routineDt.setGoal5(routine.getGoal5());
            routineDt.setStartTime(req.getStartTime());
            routineDt.setEndTime(req.getEndTime());
            // Reset all flags and times to default
            routineDt.setGoal1Flag(false);
            routineDt.setGoal2Flag(false);
            routineDt.setGoal3Flag(false);
            routineDt.setGoal4Flag(false);
            routineDt.setGoal5Flag(false);
            routineDt.setGoal1FlagTime(null);
            routineDt.setGoal2FlagTime(null);
            routineDt.setGoal3FlagTime(null);
            routineDt.setGoal4FlagTime(null);
            routineDt.setGoal5FlagTime(null);
            routineDt.setWinFlag(false);
            routineDt.setWinFlagTime(null);
        } else {
            // Only update start/end time if provided
            if (req.getStartTime() != null) routineDt.setStartTime(req.getStartTime());
            if (req.getEndTime() != null) routineDt.setEndTime(req.getEndTime());
            // Only update goal names if the routine changed
            if (!routineDt.getRoutineId().equals(req.getRoutineId())) {
                routineDt.setRoutineId(req.getRoutineId());
                routineDt.setGoal1(routine.getGoal1());
                routineDt.setGoal2(routine.getGoal2());
                routineDt.setGoal3(routine.getGoal3());
                routineDt.setGoal4(routine.getGoal4());
                routineDt.setGoal5(routine.getGoal5());
            }
            // Reset all flags and times to default
            routineDt.setGoal1Flag(false);
            routineDt.setGoal2Flag(false);
            routineDt.setGoal3Flag(false);
            routineDt.setGoal4Flag(false);
            routineDt.setGoal5Flag(false);
            routineDt.setGoal1FlagTime(null);
            routineDt.setGoal2FlagTime(null);
            routineDt.setGoal3FlagTime(null);
            routineDt.setGoal4FlagTime(null);
            routineDt.setGoal5FlagTime(null);
            routineDt.setWinFlag(false);
            routineDt.setWinFlagTime(null);
        }

        routineDtRepository.save(routineDt);

        // --- CONSISTENCY FIX: Use 'dates' for CalendarDt, not 'dateCurrent' ---
        CalendarDt calendarDt = calendarDtRepository.findByUserIdAndDates(req.getUserId(), date)
            .orElse(null);

        if (calendarDt == null) {
            calendarDt = new CalendarDt();
            calendarDt.setUserId(req.getUserId());
            calendarDt.setDates(date);
            calendarDt.setRoutineDt(routineDt); // associate with RoutineDt
        } else {
            calendarDt.setRoutineDt(routineDt); // associate with RoutineDt
        }
        calendarDtRepository.save(calendarDt);
        // --- END FIX ---

        return ResponseEntity.ok(routineDt);
    }

    @GetMapping("/user/{userId}/date/{date}")
    public RoutineDt getRoutineDtForUserAndDate(@PathVariable Long userId, @PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        // Find the CalendarDt for this user and date
        CalendarDt calendarDt = calendarDtRepository.findByUserIdAndDates(userId, localDate).orElse(null);
        // If there is a CalendarDt and it has a RoutineDt, return it; otherwise return null
        if (calendarDt != null && calendarDt.getRoutineDt() != null) {
            return calendarDt.getRoutineDt();
        }
        return null;
    }
}