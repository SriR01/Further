package com.further.repository;

import com.further.model.CalendarDt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CalendarDtRepository extends JpaRepository<CalendarDt, Long> {
    Optional<CalendarDt> findByUserIdAndDates(Long userId, LocalDate dates);
    List<CalendarDt> findByUserId(Long userId);
}