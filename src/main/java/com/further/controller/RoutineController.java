package com.further.controller;
import com.further.model.Routine;
import com.further.repository.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8088")
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
        routine.setRoutine_id(routine_id);
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
    public List<Routine> getAllRoutinesByUserId(@PathVariable Long user_id) {
        return routineRepository.findByUserId(user_id);
    }
}