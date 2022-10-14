package com.myhealth.Repositories;

import com.myhealth.Entities.Distance;
import com.myhealth.Entities.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DistanceRepository extends JpaRepository<Distance,Long> {

    Optional<Distance> findByIdAndPatientIdAndDateEquals(Long id, Long patient_id, Date date);
    List<Distance> getDistancesByPatientIdOrderByQuantityAsc(long patientId);
}
