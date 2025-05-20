package com.further.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.further.repository.ProfileRepository;
import com.further.model.Profile;

@RestController
@CrossOrigin(origins = "http://localhost:8088")
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @PostMapping
    public Profile createProfile(@RequestBody Profile profile) {
        return profileRepository.save(profile);
    }

    @PutMapping("/{user_id}")
    public Profile updateProfile(@PathVariable Long user_id, @RequestBody Profile profile) {
        profile.setUserId(user_id);
        return profileRepository.save(profile);
    }

    @DeleteMapping("/delete/{user_id}")
    public void deleteProfile(@PathVariable Long user_id) {
        profileRepository.deleteById(user_id);
    }

    @GetMapping("/{user_id}")
    public Profile getProfileByUserId(@PathVariable Long user_id) {
        return profileRepository.findById(user_id).orElse(null);
    }
}