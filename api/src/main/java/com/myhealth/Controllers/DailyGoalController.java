package com.myhealth.Controllers;

import java.util.List;

import com.myhealth.Entities.DailyGoal;
import com.myhealth.Services.DailyGoalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dailyGoal")
public class DailyGoalController {

	@Autowired
	private DailyGoalService dailyGoalService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<DailyGoal>> fetchAll() {
		try {
			List<DailyGoal> dailyGoal = dailyGoalService.findAll();
			return new ResponseEntity<List<DailyGoal>>(dailyGoal, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/{dailyGoalId}")
	public ResponseEntity<DailyGoal> getDailyGoalById(@PathVariable("dailyGoalId") Long dailyGoalId) {
		try {
			DailyGoal dailyGoal = dailyGoalService.findById(dailyGoalId);
			if (dailyGoal != null) {
				return new ResponseEntity<DailyGoal>(dailyGoal, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "{dailyGoalId}")
	public ResponseEntity<?> updateDailyGoal(@PathVariable("dailyGoalId") Long dailyGoalId,
			@RequestBody DailyGoal dailyGoal) throws Exception {
		DailyGoal currentDailyGoal = dailyGoalService.update(dailyGoal, dailyGoalId);
		return ResponseEntity.ok(currentDailyGoal);
	}

	@DeleteMapping(value = "{dailyGoalId}")
	public void deleteDailyGoal(@PathVariable("dailyGoalId") Long dailyGoalId) throws Exception {
		dailyGoalService.deleteById(dailyGoalId);
	}

	// patient controller

	// @PostMapping(path = "{patientId}/mealPlans")
	// public ResponseEntity<MealPlan> createMealPlan(@PathVariable("patientId")
	// Long patientId,@RequestBody MealPlan mealPlan, BindingResult result){
	//
	// MealPlan mealPlan1 = mealPlanService.createMealPlan(patientId,mealPlan);
	// return ResponseEntity.status(HttpStatus.CREATED).body(mealPlan1);
	// }
}
