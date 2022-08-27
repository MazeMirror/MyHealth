package com.myhealth.Repositories;

import com.myhealth.Entities.DailyGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyGoalRepository extends JpaRepository<DailyGoal,Long> {
}
