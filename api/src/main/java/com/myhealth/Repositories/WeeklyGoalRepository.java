package com.myhealth.Repositories;

import com.myhealth.Entities.DailyGoal;
import com.myhealth.Entities.WeeklyGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeeklyGoalRepository extends JpaRepository<WeeklyGoal,Long> {
    List<WeeklyGoal> getWeeklyGoalsByPatientId(long patientId);
    List<WeeklyGoal> getWeeklyGoalsByPatientIdAndActivityId(long patientId,long activityId);
}
