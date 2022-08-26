package com.myhealth.Repositories;

import com.myhealth.Entities.Profile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
