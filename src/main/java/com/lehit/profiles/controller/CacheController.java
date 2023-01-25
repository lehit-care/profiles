package com.lehit.profiles.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/profiles")
public class CacheController {

    private final EntityManager em;

    @Operation(summary = "Clears all the caches for the service.")
    @DeleteMapping("/caches")
    public void clearAllCaches(){
        em.getEntityManagerFactory().getCache().evictAll();
        em.getEntityManagerFactory().getCache().unwrap(org.hibernate.Cache.class).evictAllRegions();
    }
}
