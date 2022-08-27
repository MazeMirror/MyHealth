package com.myhealth.Services;

import java.util.List;

import com.myhealth.Entities.MealPlan;
import com.myhealth.Repositories.MealPlanRepository;
import com.myhealth.Repositories.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealPlanService {
	@Autowired
	private MealPlanRepository mealPlanRepository;

	@Autowired
	private PatientRepository patientRepository;

	public List<MealPlan> findAll() throws Exception {
		return mealPlanRepository.findAll();
	}

	public MealPlan findById(Long aLong) throws Exception {
		MealPlan mealPlan = mealPlanRepository.findById(aLong).orElse(null);

		return mealPlan;
	}

	public MealPlan update(MealPlan entity, Long aLong) throws Exception {
		MealPlan mealPlan = mealPlanRepository.findById(aLong)
				.orElseThrow(() -> new RuntimeException("meal plan not found"));
		mealPlan.setName(entity.getName());
		mealPlan.setDescription(entity.getDescription());

		return mealPlanRepository.save(mealPlan);
	}

	public void deleteById(Long aLong) throws Exception {
		MealPlan mealPlan = mealPlanRepository.findById(aLong)
				.orElseThrow(() -> new RuntimeException("meal plan not found"));
		mealPlanRepository.deleteById(aLong);
	}

	public MealPlan createMealPlan(Long patientId, MealPlan mealPlan) {

		return patientRepository.findById(patientId).map(
				patient -> {
					mealPlan.setPatient(patient);
					return mealPlanRepository.save(mealPlan);
				}).orElseThrow(() -> new RuntimeException("patient not found"));

		// return mealPlanRepository.save(mealPlan);
	}
}
