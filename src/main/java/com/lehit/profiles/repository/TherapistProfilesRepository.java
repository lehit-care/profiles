package com.lehit.profiles.repository;


import com.lehit.profiles.model.TherapistProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface TherapistProfilesRepository extends JpaRepository<TherapistProfile, UUID>, JpaSpecificationExecutor<TherapistProfile>{

}
