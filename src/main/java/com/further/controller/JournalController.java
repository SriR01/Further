package com.further.controller;
import com.further.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.further.model.Journal;

import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/journals")
public class JournalController {

    private final JournalRepository journalRepository;

    @Autowired
    public JournalController(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    @PostMapping
    public Journal createJournal(@RequestBody Journal journal) {
        return journalRepository.save(journal);
    }

    @PutMapping("/{entry_id}")
    public Journal updateJournal(@PathVariable Long entry_id, @RequestBody Journal journal) {
        journal.setJournalId(entry_id);
        return journalRepository.save(journal);
    }

    @DeleteMapping("/delete/{entry_id}")
    public void deleteJournal(@PathVariable Long entry_id) {
        journalRepository.deleteById(entry_id);
    }

    @GetMapping("/{entry_id}")
    public Journal getJournalById(@PathVariable Long entry_id) {
        return journalRepository.findById(entry_id).orElse(null);
    }

    @GetMapping("/{user_id}/{entryDate}")
    public Journal getJournalByUserIdAndEntryDate(@PathVariable Long user_id, @PathVariable String entryDate) {
        return journalRepository.findByUserIdAndEntryDate(user_id, LocalDate.parse(entryDate)).orElse(null);
    }

    @GetMapping("/users/{user_id}")
    public List<Journal> getAllJournalsByUserId(@PathVariable Long user_id) {
        return journalRepository.findByUserId(user_id);
    }
}