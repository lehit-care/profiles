package com.lehit.profiles.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lehit.profiles.model.audit.Auditable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.Persistable;

import java.util.UUID;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true) @EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Cache(region = "adminProfileCache", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AdminProfile extends Auditable implements Persistable<UUID>{
    @Schema( accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @Column(name = "id", updatable= false)
    @ToString.Include
    @EqualsAndHashCode.Include
    private UUID id;

    @Email
    @Column(unique = true)
    @ToString.Include
    private String email;

    private String firstName;

    private String lastName;

    private String mobileNumber;

    private String additionalDetails;

    private String avatarUrl;



    //    avoid select before insert operation
    @JsonIgnore
    @Transient
    private boolean isNew = true;

    @JsonIgnore
    @Override
    public boolean isNew() {
        return isNew;
    }

    @PrePersist
    @PostLoad
    void markNotNew() {
        this.isNew = false;
    }

}
