package com.lehit.profiles.service;

import com.lehit.profiles.model.UserProfile;
import com.lehit.profiles.repository.AuthorsRepository;
import com.lehit.profiles.repository.UserProfilesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.Asserts;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserProfileService {
    private final UserProfilesRepository profilesRepository;

    @Transactional
    public UserProfile saveProfile(UUID profileId, UserProfile userProfile){
        userProfile.setId(profileId);
        return profilesRepository.save(userProfile);
    }


    public UserProfile findById(UUID profileId){
        return profilesRepository.findById(profileId).orElseThrow();
    }

    @Transactional
    public UserProfile setTimeZone(UUID profileId, String zoneId){
        UserProfile profile = profilesRepository.findById(profileId).orElseThrow();

        Asserts.check(Set.of(TimeZone.getAvailableIDs()).contains(zoneId), "Invalid timeZone. The list of available zones: https://en.wikipedia.org/wiki/List_of_tz_database_time_zones");

        profile.setTimeZoneId(zoneId);
        return profile;
    }

    @Transactional
    public void deleteProfile(UUID profileId){
        profilesRepository.deleteById(profileId);
    }


    public Optional<UserProfile> getUserProfile(UUID profileId){
        return profilesRepository.findById(profileId);
    }

}
