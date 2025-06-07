package com.further.repository;

import com.further.model.RoutineDt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RoutineDtRepository extends JpaRepository<RoutineDt, Long> {
    List<RoutineDt> findByUserId(Long userId);
    List<RoutineDt> findByUserIdAndDateCurrent(Long userId, LocalDate dateCurrent);
}