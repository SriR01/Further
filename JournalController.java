package com.Further.controller;

import com.Further.model.Journal;
import com.Further.dao.JournalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8088")
@RequestMapping("/journals")
public class JournalController {

    private final JournalDao journalDao;

    @Autowired
    public JournalController(JournalDao journalDao) {
        this.journalDao = journalDao;
    }

    @PostMapping
    public void createJournal(@RequestBody Journal journal) {
        journalDao.createJournal(journal);
    }

    @PutMapping("/{entryId}")
    public void updateJournal(@PathVariable int entryId, @RequestBody Journal journal) {
        journal.setEntry_id(entryId);
        journalDao.updateJournal(journal);
    }

    @DeleteMapping("/delete/{entryId}")
    public void deleteJournal(@PathVariable int entryId) {
        journalDao.deleteJournal(entryId);
    }

    @GetMapping("/{entryId}")
    public Journal getJournalById(@PathVariable int entryId) {
        return journalDao.getJournalById(entryId);
    }

    @GetMapping("/{userId}/{dateValue}")
    public Journal getJournalByUserIdAndDateValue(@PathVariable int userId, @PathVariable String dateValue){
        return journalDao.getJournalByUserIdAndDateValue(userId, dateValue);
    }

    @GetMapping("/users/{userId}")
    public List<Journal> getAllJournalsByUserId(@PathVariable int userId) {
        return journalDao.getAllJournalsByUserId(userId);
    }
}
