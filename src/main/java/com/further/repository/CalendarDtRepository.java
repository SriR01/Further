package com.further.repository;

import com.further.model.CalendarDt;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CalendarDtRepository extends JpaRepository<CalendarDt, Long> {
    List<CalendarDt> findByUser_id(Long user_id);
}