package com.myhealth.Repositories;

import com.myhealth.Entities.Profile;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUserId(long id);
    List<Profile> findProfileByNameContaining(String name);
    List<Profile> findProfilesByRoleId(long roleId);
}
