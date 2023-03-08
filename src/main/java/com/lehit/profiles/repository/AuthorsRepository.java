package com.lehit.profiles.repository;


import com.lehit.profiles.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface AuthorsRepository extends JpaRepository<Author, UUID>, JpaSpecificationExecutor<Author>{

}
