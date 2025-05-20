package com.further.repository;

import com.further.model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface JournalRepository extends JpaRepository<Journal, Long> {
    Optional<Journal> findByUserIdAndEntryDate(Long userId, LocalDate entryDate);
    List<Journal> findByUserId(Long userId);
}