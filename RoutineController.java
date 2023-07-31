package com.Further.controller;

import com.Further.model.Routine;
import com.Further.dao.RoutineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8088")
@RequestMapping("/routine")
public class RoutineController {

    private final RoutineDao routineDao;

    @Autowired
    public RoutineController(RoutineDao routineDao) {
        this.routineDao = routineDao;
    }

    @PostMapping
    public void createRoutine(@RequestBody Routine routine) {
        routineDao.createRoutine(routine);
    }

    @PutMapping("/{routine_id}")
    public void updateRoutine(@PathVariable int routine_id, @RequestBody Routine routine) {
        routine.setRoutine_id(routine_id);
        routineDao.updateRoutine(routine);
    }

    @DeleteMapping("/delete/{routine_id}")
    public void deleteRoutine(@PathVariable int routine_id) {
        routineDao.deleteRoutine(routine_id);
    }

    @GetMapping("/{routine_id}")
    public Routine getRoutineById(@PathVariable int routine_id) {
        return routineDao.getRoutineById(routine_id);
    }

    @GetMapping("/user/{user_id}")
    public List<Routine> getAllRoutinesByUserId(@PathVariable int user_id) {
        return routineDao.getAllRoutinesByUserId(user_id);
    }
}
