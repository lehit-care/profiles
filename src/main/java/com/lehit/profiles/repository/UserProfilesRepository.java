package com.lehit.profiles.repository;


import com.lehit.profiles.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface UserProfilesRepository extends JpaRepository<UserProfile, UUID>, JpaSpecificationExecutor<UserProfile>{
}
