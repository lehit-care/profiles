package com.lehit.profiles.repository;


import com.lehit.profiles.model.AdminProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface AdminProfilesRepository extends JpaRepository<AdminProfile, UUID>, JpaSpecificationExecutor<AdminProfile>{

}
