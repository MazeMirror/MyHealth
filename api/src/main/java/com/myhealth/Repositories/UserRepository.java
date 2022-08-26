package com.myhealth.Repositories;

import com.myhealth.Entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
