package com.myhealth.Repositories;

import com.myhealth.Entities.Specialist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialistRepository extends JpaRepository<Specialist, Long> {
    Optional<Specialist> findByProfileId(Long profileId);
}
