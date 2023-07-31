package com.Further.controller;

import com.Further.model.Profile;
import com.Further.dao.ProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8088")
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileDao profileDao;

    @Autowired
    public ProfileController(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    @PostMapping
    public void createProfile(@RequestBody Profile profile) {
        profileDao.createProfile(profile);
    }

    @PutMapping("/{user_id}")
    public void updateProfile(@PathVariable int user_id, @RequestBody Profile profile) {
        profileDao.updateProfile(profile);
    }

    @DeleteMapping("/delete/{user_id}")
    public void deleteProfile(@PathVariable int user_id) {
        profileDao.deleteProfile(user_id);
    }

    @GetMapping("/{user_id}")
    public Profile getProfileByUserId(@PathVariable int user_id) {
        return profileDao.getProfileByUserId(user_id);
    }
}
