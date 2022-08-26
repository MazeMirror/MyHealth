package com.myhealth.Repositories;

import com.myhealth.Entities.Specialist;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialistRepository extends JpaRepository<Specialist, Long> {
}
