package com.lehit.profiles.controller;

import com.lehit.profiles.client.kafka.KafkaProfileProducer;
import com.lehit.profiles.model.UserProfile;
import com.lehit.profiles.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/profiles")
@Slf4j
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService profileService;
    private final KafkaProfileProducer kafkaProfileProducer;

    @Value("${spring.kafka.enabled}")
    private boolean kafkaEnabled;

    @Operation(summary = "Create a client Profile.")
    @PutMapping("/{profileId}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserProfile saveProfile(@PathVariable UUID profileId, @RequestBody UserProfile userProfile){
        UserProfile profile = profileService.saveProfile(profileId, userProfile);
        if(kafkaEnabled)
            kafkaProfileProducer.sendCreateEvent(profile);
        return profile;
    }


    @Operation(summary = "Sets client timeZone.")
    @PostMapping("/{profileId}/set-timezone")
    public UserProfile setUserTimeZone(@PathVariable UUID profileId,
                                                  @Parameter(description = "Allowed zones:  https://en.wikipedia.org/wiki/List_of_tz_database_time_zones. (TZ database name)")
                                                  @RequestHeader String timeZoneId){
        return profileService.setTimeZone(profileId, timeZoneId);
    }


    @Operation(summary = "Deletes a client Profile. Deletes all the user data.")
    @DeleteMapping("/{profileId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@PathVariable UUID profileId){
        profileService.deleteProfile(profileId);
        if(kafkaEnabled)
            kafkaProfileProducer.sendDeleteEvent(UserProfile.builder().id(profileId).build());
    }

}
