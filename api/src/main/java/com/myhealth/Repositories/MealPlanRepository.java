package com.myhealth.Repositories;

import com.myhealth.Entities.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealPlanRepository extends JpaRepository<MealPlan,Long> {
}
