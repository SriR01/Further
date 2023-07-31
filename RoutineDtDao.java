package com.Further.dao;

import com.Further.model.RoutineDt;

import java.util.List;

public interface RoutineDtDao {

    void createRoutineDt(RoutineDt routineDt);

    void updateRoutineDt(RoutineDt routineDt);

    void deleteRoutineDt(int routineId);

    RoutineDt getRoutineDtById(int routineId);

    List<RoutineDt> getRoutineDtsByUserId(int userId);

    RoutineDt getRoutineDtByUserIdAndDateValue(int userId, String dateValue);
}
