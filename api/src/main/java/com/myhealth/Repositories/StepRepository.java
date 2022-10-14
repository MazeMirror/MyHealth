package com.myhealth.Repositories;

import com.myhealth.Entities.DailyGoal;
import com.myhealth.Entities.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface StepRepository extends JpaRepository<Step,Long> {
   Optional<Step> findByIdAndPatientIdAndDateEquals(Long id, Long patient_id, Date date);
    List<Step> getStepsByPatientIdOrderByQuantityAsc(long patientId);
}
