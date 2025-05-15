package com.further.controller;
    
import com.further.model.RoutineDt;
import com.further.repository.RoutineDtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8088")
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

    @PutMapping("/{id}")
    public RoutineDt updateRoutineDt(@PathVariable Long id, @RequestBody RoutineDt routineDt) {
        routineDt.setId(id);
        return routineDtRepository.save(routineDt);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRoutineDt(@PathVariable Long id) {
        routineDtRepository.deleteById(id);
    }

    @GetMapping("/user/{user_id}")
    public List<RoutineDt> getRoutineDtsByUserId(@PathVariable Long user_id) {
        return routineDtRepository.findByUserId(user_id);
    }

    @GetMapping("/{id}")
    public RoutineDt getRoutineDtById(@PathVariable Long id) {
        return routineDtRepository.findById(id).orElse(null);
    }

    // If you want to query by userId and date, make sure your repository has this method:
    // Optional<RoutineDt> findByUserIdAndDateCurrent(int userId, LocalDate dateCurrent);
    // And then:
    // @GetMapping("/{userId}/{dateValue}")
    // public RoutineDt getRoutineDtByUserIdAndDateValue(@PathVariable int userId, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateValue){
    //     return routineDtRepository.findByUserIdAndDateCurrent(userId, dateValue).orElse(null);
    // }
}