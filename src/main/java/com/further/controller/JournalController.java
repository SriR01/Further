package com.further.controller;
import com.further.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.further.model.Journal;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8088")
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

    @PutMapping("/{entryId}")
    public Journal updateJournal(@PathVariable Long entry_id, @RequestBody Journal journal) {
        journal.setEntry_id(entry_id);
        return journalRepository.save(journal);
    }

    @DeleteMapping("/delete/{entryId}")
    public void deleteJournal(@PathVariable int entryId) {
        journalRepository.deleteById(entryId);
    }

    @GetMapping("/{entryId}")
    public Journal getJournalById(@PathVariable int entryId) {
        return journalRepository.findById(entryId).orElse(null);
    }

    @GetMapping("/{userId}/{dateValue}")
    public Journal getJournalByUserIdAndDateValue(@PathVariable int userId, @PathVariable String dateValue){
        return journalRepository.findByUserIdAndDateValue(userId, dateValue).orElse(null);
    }

    @GetMapping("/users/{userId}")
    public List<Journal> getAllJournalsByUserId(@PathVariable int userId) {
        return journalRepository.findByUserId(userId);
    }
}