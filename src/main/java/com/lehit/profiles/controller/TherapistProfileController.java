package com.lehit.profiles.controller;

import com.lehit.profiles.model.TherapistProfile;
import com.lehit.profiles.repository.TherapistProfilesRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/therapist-profiles")
@Slf4j
@RequiredArgsConstructor
public class TherapistProfileController {
    private final TherapistProfilesRepository profilesRepository;


    @Operation(summary = "Creates a Therapist Profile.")
    @PutMapping("/{accountId}")
    @ResponseStatus(HttpStatus.CREATED)
    public TherapistProfile saveProfile(@PathVariable UUID accountId, @RequestBody TherapistProfile profile){
        profile.setId(accountId);
        return profilesRepository.save(profile);
    }

    @GetMapping("/{profileId}")
    public TherapistProfile getMyProfile( @PathVariable UUID profileId){
        return profilesRepository.findById(profileId).orElseThrow();
    }

    @Operation(summary = "Deletes a Therapist Profile.")
    @DeleteMapping("/{profileId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@PathVariable UUID profileId){
        profilesRepository.deleteById(profileId);
    }
}
