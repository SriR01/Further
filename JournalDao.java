package com.Further.dao;

import com.Further.model.Journal;

import java.util.List;

public interface JournalDao {

    void createJournal(Journal journal);

    void updateJournal(Journal journal);

    void deleteJournal(int entryId);

    Journal getJournalById(int entryId);

    List<Journal> getAllJournalsByUserId(int userId);

    Journal getJournalByUserIdAndDateValue(int userId, String dateValue);

}
