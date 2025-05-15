package com.further.repository;

import com.further.model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JournalRepository extends JpaRepository<Journal, Integer> {
    List<Journal> findByUserId(int userId);
    Optional<Journal> findByUserIdAndDateValue(int userId, String dateValue);
}