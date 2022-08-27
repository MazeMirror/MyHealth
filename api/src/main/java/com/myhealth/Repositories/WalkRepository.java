package com.myhealth.Repositories;

import com.myhealth.Entities.Walk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalkRepository extends JpaRepository<Walk,Long> {
}
