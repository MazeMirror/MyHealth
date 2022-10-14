package com.myhealth.Repositories;

import com.myhealth.Entities.Distance;
import com.myhealth.Entities.Kilocalorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface KilocalorieRepository extends JpaRepository<Kilocalorie,Long> {
    Optional<Kilocalorie> findByIdAndPatientIdAndDateEquals(Long id, Long patient_id, Date date);
    List<Kilocalorie> getKilocaloriesByPatientIdOrderByQuantityAsc(long patientId);
}
