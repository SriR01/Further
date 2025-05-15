package com.further.repository;
import com.further.model.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Integer> {
    List<Routine> findByUserId(int user_id);
}