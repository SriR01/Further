package com.further.repository;
import com.further.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Integer> {
    List<Goal> findByUserId(int user_id);
}