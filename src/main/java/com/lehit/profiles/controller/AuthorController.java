package com.lehit.profiles.controller;

import com.lehit.profiles.model.Author;
import com.lehit.profiles.repository.AuthorsRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/authors")
@Slf4j
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorsRepository profilesRepository;


    @Operation(summary = "Creates an Author Profile.")
    @PutMapping("/{accountId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Author saveProfile(@PathVariable UUID accountId, @RequestBody Author profile){
        profile.setId(accountId);
        return profilesRepository.save(profile);
    }

    @GetMapping("/{profileId}")
    public Author getMyProfile(@PathVariable UUID profileId){
        return profilesRepository.findById(profileId).orElseThrow();
    }

    @Operation(summary = "Deletes an Author Profile.")
    @DeleteMapping("/{profileId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@PathVariable UUID profileId){
        profilesRepository.deleteById(profileId);
    }
}
