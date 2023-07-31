package com.Further.dao;

import com.Further.model.Profile;

public interface ProfileDao {

    void createProfile(Profile profile);

    void updateProfile(Profile profile);

    void deleteProfile(int userId);

    Profile getProfileByUserId(int userId);
}
