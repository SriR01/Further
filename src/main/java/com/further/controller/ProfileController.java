package com.further.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.further.repository.ProfileRepository;
import com.further.model.Profile;

import java.util.Optional;

@RestController
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
    public Profile updateProfile(@PathVariable("user_id") Long userId, @RequestBody Profile profile) {
        Optional<Profile> existing = profileRepository.findByUserId(userId);
        if (existing.isPresent()) {
            Profile existingProfile = existing.get();
            existingProfile.setBio(profile.getBio());
            existingProfile.setEmail(profile.getEmail());
            existingProfile.setFirstName(profile.getFirstName());
            existingProfile.setLastName(profile.getLastName());
            // ... any other fields ...
            return profileRepository.save(existingProfile);
        } else {
            // Set the userId to ensure it's correct
            profile.setUserId(userId);
            return profileRepository.save(profile);
        }
    }

    @DeleteMapping("/delete/{user_id}")
    public void deleteProfile(@PathVariable Long user_id) {
        profileRepository.deleteById(user_id);
    }

    @GetMapping("/{user_id}")
    public Profile getProfileByUserId(@PathVariable("user_id") Long userId) {
        return profileRepository.findByUserId(userId).orElse(null);
    }
}