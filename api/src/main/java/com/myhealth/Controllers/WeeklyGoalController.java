package com.myhealth.Controllers;

import java.util.List;

import com.myhealth.Entities.WeeklyGoal;
import com.myhealth.Services.WeeklyGoalService;

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
@RequestMapping("/weeklyGoal")
public class WeeklyGoalController {

	@Autowired
	private WeeklyGoalService weeklyGoalService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<WeeklyGoal>> fetchAll() {
		try {
			List<WeeklyGoal> weeklyGoal = weeklyGoalService.findAll();
			return new ResponseEntity<List<WeeklyGoal>>(weeklyGoal, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/{weeklyGoalId}")
	public ResponseEntity<WeeklyGoal> getWeeklyGoalById(@PathVariable("weeklyGoalId") Long weeklyGoalId) {
		try {
			WeeklyGoal weeklyGoal = weeklyGoalService.findById(weeklyGoalId);
			if (weeklyGoal != null) {
				return new ResponseEntity<WeeklyGoal>(weeklyGoal, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "{weeklyGoalId}")
	public ResponseEntity<?> updateWeeklyGoal(@PathVariable("weeklyGoalId") Long weeklyGoalId,
			@RequestBody WeeklyGoal weeklyGoal) throws Exception {
		WeeklyGoal currentWeeklyGoal = weeklyGoalService.update(weeklyGoal, weeklyGoalId);
		return ResponseEntity.ok(currentWeeklyGoal);
	}

	@DeleteMapping(value = "{weeklyGoalId}")
	public void deleteWeeklyGoal(@PathVariable("weeklyGoalId") Long weeklyGoalId) throws Exception {
		weeklyGoalService.deleteById(weeklyGoalId);
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
