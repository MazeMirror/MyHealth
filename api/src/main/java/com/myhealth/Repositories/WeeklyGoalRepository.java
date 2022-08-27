package com.myhealth.Repositories;

import com.myhealth.Entities.WeeklyGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeeklyGoalRepository extends JpaRepository<WeeklyGoal,Long> {
}
