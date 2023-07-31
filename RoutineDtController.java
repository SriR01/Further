package com.Further.controller;

import com.Further.model.Journal;
import com.Further.model.RoutineDt;
import com.Further.dao.RoutineDtDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8088")
@RequestMapping("/routine-dt")
public class RoutineDtController {

    private final RoutineDtDao routineDtDao;

    @Autowired
    public RoutineDtController(RoutineDtDao routineDtDao) {
        this.routineDtDao = routineDtDao;
    }

    @PostMapping()
    public void createRoutineDt(@RequestBody RoutineDt routineDt) {
        routineDtDao.createRoutineDt(routineDt);
    }

    @PutMapping()
    public void updateRoutineDtByDate(@RequestBody RoutineDt routineDt) {
        routineDtDao.updateRoutineDt(routineDt);
    }

    @DeleteMapping("/delete/{routine_id}")
    public void deleteRoutineDt(@PathVariable int routine_id) {
        routineDtDao.deleteRoutineDt(routine_id);
    }

    @GetMapping("/user/{user_id}")
    public List<RoutineDt> getRoutineDtsByUserId(@PathVariable int user_id) {
        return routineDtDao.getRoutineDtsByUserId(user_id);
    }

    @GetMapping("/{routine_id}")
    public RoutineDt getRoutineDtById(@PathVariable int routine_id) {
        return routineDtDao.getRoutineDtById(routine_id);
    }

    @GetMapping("/{userId}/{dateValue}")
    public RoutineDt getRoutineDtByUserIdAndDateValue(@PathVariable int userId, @PathVariable String dateValue){
        return routineDtDao.getRoutineDtByUserIdAndDateValue(userId, dateValue);
    }

}
