package com.further.model;
import java.time.LocalDate;
import jakarta.persistence.*;



@Entity
@Table(name = "journal")
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "journal_id")
    private Long journalId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "entry_subject")
    private String entrySubject;

    @Column(name = "entry_data")
    private String entryData;

    @Column(name = "entry_date")
    private LocalDate entryDate;

    public Journal(Long journalId, Long userId, String entrySubject, String entryData, LocalDate entryDate) {
        this.journalId = journalId;
        this.userId = userId;
        this.entrySubject = entrySubject;
        this.entryData = entryData;
        this.entryDate = entryDate;
    }

    public Journal() {}

    public Long getJournalId() {
        return journalId;
    }

    public void setJournalId(Long journalId) {
        this.journalId = journalId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEntrySubject() {
        return entrySubject;
    }

    public void setEntrySubject(String entrySubject) {
        this.entrySubject = entrySubject;
    }

    public String getEntryData() {
        return entryData;
    }

    public void setEntryData(String entryData) {
        this.entryData = entryData;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

}
