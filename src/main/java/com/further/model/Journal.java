package com.further.model;
import java.time.LocalDate;
import jakarta.persistence.*;



@Entity
@Table(name = "journal")
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entry_id")
    private static final long serialVersionUID = 1L;
    private Long entry_id;

    private int user_id;
    private String entry_subject;
    private String entry_data;
    private LocalDate entry_date;

    public Journal(Long entry_id, int user_id, String entry_subject, String entry_data, LocalDate entry_date) {
        this.entry_id = entry_id;
        this.user_id = user_id;
        this.entry_subject = entry_subject;
        this.entry_data = entry_data;
        this.entry_date = entry_date;
    }

    public Journal() {
    }

    public Long getEntry_id() {
        return entry_id;
    }

    public void setEntry_id(Long entry_id) {
        this.entry_id = entry_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getEntry_subject() {
        return entry_subject;
    }

    public void setEntry_subject(String entry_subject) {
        this.entry_subject = entry_subject;
    }

    public String getEntry_data() {
        return entry_data;
    }

    public void setEntry_data(String entry_data) {
        this.entry_data = entry_data;
    }

    public LocalDate getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(LocalDate entry_date) {
        this.entry_date = entry_date;
    }

}
