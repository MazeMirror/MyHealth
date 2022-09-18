package com.myhealth.Controllers;

import java.util.List;

import com.myhealth.Dto.Responses.PatientDtoResponse;
import com.myhealth.Entities.DailyGoal;
import com.myhealth.Entities.MealPlan;
import com.myhealth.Entities.Patient;
import com.myhealth.Entities.WeeklyGoal;
import com.myhealth.Services.DailyGoalService;
import com.myhealth.Services.MealPlanService;
import com.myhealth.Services.PatientService;
import com.myhealth.Services.WeeklyGoalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@Autowired
	private MealPlanService mealPlanService;

	@Autowired
	private DailyGoalService dailyGoalService;

	@Autowired
	private WeeklyGoalService weeklyGoalService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<PatientDtoResponse>> fetchAll() {
		try {
			List<PatientDtoResponse> patients = patientService.findAll();
			return new ResponseEntity<List<PatientDtoResponse>>(patients, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "{patientId}/mealPlans")
	public ResponseEntity<MealPlan> createMealPlan(@PathVariable("patientId") Long patientId,
			@RequestBody MealPlan mealPlan) {

		MealPlan mealPlan1 = mealPlanService.createMealPlan(patientId, mealPlan);
		return ResponseEntity.status(HttpStatus.CREATED).body(mealPlan1);
	}

	@PostMapping(path = "{patientId}/weeklyGoal")
	public ResponseEntity<WeeklyGoal> createWeeklyGoal(@PathVariable("patientId") Long patientId,
			@RequestBody WeeklyGoal weeklyGoal) {

		WeeklyGoal weeklyGoal1 = weeklyGoalService.createWeeklyGoal(patientId, weeklyGoal);
		return ResponseEntity.status(HttpStatus.CREATED).body(weeklyGoal1);
	}

	@PostMapping(path = "{patientId}/dailyGoal")
	public ResponseEntity<DailyGoal> createDailyGoal(@PathVariable("patientId") Long patientId,
			@RequestBody DailyGoal dailyGoal) {

		DailyGoal dailyGoal1 = dailyGoalService.createDailyGoal(patientId, dailyGoal);
		return ResponseEntity.status(HttpStatus.CREATED).body(dailyGoal1);
	}
}
