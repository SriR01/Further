package com.Further.dao;

import com.Further.model.Routine;

import java.util.List;

public interface RoutineDao {

    void createRoutine(Routine routine);

    void updateRoutine(Routine routine);

    void deleteRoutine(int routineId);

    Routine getRoutineById(int routineId);

    List<Routine> getAllRoutinesByUserId(int userId);
}
