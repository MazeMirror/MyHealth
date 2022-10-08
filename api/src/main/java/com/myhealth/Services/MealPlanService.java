package com.myhealth.Services;

import java.util.List;

import com.myhealth.Common.EntityDtoConverter;
import com.myhealth.Dto.Requests.MealPlanDtoRequest;
import com.myhealth.Dto.Responses.MealPlanDtoResponse;
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

	@Autowired
	EntityDtoConverter entityDtoConverter;

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

	public List<MealPlanDtoResponse> getMealPlansByPatientId(Long patientId){

		List<MealPlan> mealPlans = mealPlanRepository.getMealPlansByPatientId(patientId);
		return entityDtoConverter.convertMealPlanToDto(mealPlans);
	}

	public MealPlanDtoResponse updateMealPlanByPatientIdAndId(Long patientId, Long mealPlanId, MealPlanDtoRequest mealPlanDtoRequest) {
		var patient = patientRepository.findById(patientId).orElseThrow(() -> new RuntimeException("patient not found by Id "+patientId));
		//var activity = activityRepository.findById(mealPlanDtoRequest.getActivityId()).orElseThrow(() -> new RuntimeException("activity not found by Id "+dailyGoalDtoRequest.getActivityId()));
		var mealPlan = mealPlanRepository.findById(mealPlanId).orElseThrow(() -> new RuntimeException("mealPlan not found by Id "+mealPlanId));

		mealPlan.setName(mealPlanDtoRequest.getName());
		mealPlan.setDescription(mealPlanDtoRequest.getDescription());

		var response = mealPlanRepository.save(mealPlan);
		return entityDtoConverter.convertMealPlanToDto(response);
	}

}
