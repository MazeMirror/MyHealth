package com.myhealth.Repositories;

import com.myhealth.Entities.Distance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistanceRepository extends JpaRepository<Distance,Long> {
}
