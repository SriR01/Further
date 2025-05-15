package com.further.model;
import jakarta.persistence.*;


@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profile_id;

    @Column(name = "user_id")
    private int user_id;
    private String bio;

    private String email;
    private String first_name;
    private String last_name;

    public Profile(Long profile_id, int user_id, String bio, String email, String first_name, String last_name) {
        this.profile_id = profile_id;
        this.user_id = user_id;
        this.bio = bio;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Profile() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
