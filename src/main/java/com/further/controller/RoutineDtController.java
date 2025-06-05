package com.further.controller;
    
import com.further.dto.SetTodayRoutineRequest;
import com.further.model.RoutineDt;
import com.further.repository.RoutineDtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/routine-dt")
public class RoutineDtController {

    private final RoutineDtRepository routineDtRepository;

    @Autowired
    public RoutineDtController(RoutineDtRepository routineDtRepository) {
        this.routineDtRepository = routineDtRepository;
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
        LocalDate date;
        try {
            date = LocalDate.parse(req.getDate());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid date format. Use YYYY-MM-DD.");
        }

        RoutineDt routineDt = routineDtRepository
            .findByUserIdAndDateCurrent(req.getUserId(), date)
            .orElse(new RoutineDt());
        routineDt.setUserId(req.getUserId());
        routineDt.setDateCurrent(date);
        routineDt.setRoutineId(req.getRoutineId());
        routineDtRepository.save(routineDt);
        return ResponseEntity.ok(routineDt);
    }
}