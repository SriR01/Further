package com.further.controller;
import com.further.model.Routine;
import com.further.repository.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routine")
public class RoutineController {

    private final RoutineRepository routineRepository;

    @Autowired
    public RoutineController(RoutineRepository routineRepository) {
        this.routineRepository = routineRepository;
    }

    @PostMapping
    public Routine createRoutine(@RequestBody Routine routine) {
        return routineRepository.save(routine);
    }

    @PutMapping("/{routine_id}")
    public Routine updateRoutine(@PathVariable Long routine_id, @RequestBody Routine routine) {
        routine.setRoutineId(routine_id);
        return routineRepository.save(routine);
    }

    @DeleteMapping("/delete/{routine_id}")
    public void deleteRoutine(@PathVariable Long routine_id) {
        routineRepository.deleteById(routine_id);
    }

    @GetMapping("/{routine_id}")
    public Routine getRoutineById(@PathVariable Long routine_id) {
        return routineRepository.findById(routine_id).orElse(null);
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<List<Routine>> getAllRoutinesByUserId(@PathVariable("user_id") Long userId) {
        List<Routine> routines = routineRepository.findByUserId(userId);
        return ResponseEntity.ok(routines);
    }
}