package com.further.repository;
import com.further.model.RoutineDt;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoutineDtRepository extends JpaRepository<RoutineDt, Long> {
    List<RoutineDt> findByUserId(int user_id);
    List<RoutineDt> findByRoutineId(int routine_id);
}