package com.further.repository;

import com.further.model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JournalRepository extends JpaRepository<Journal, Long> {
    List<Journal> findByUserId(Long user_id);
    Optional<Journal> findByUserIdAndDateValue(Long user_id, String dateValue);
}