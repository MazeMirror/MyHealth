package com.myhealth.Controllers;

import java.util.List;

import com.myhealth.Entities.MealPlan;
import com.myhealth.Services.MealPlanService;

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
@RequestMapping("/mealPlan")
public class MealPlanController {

	@Autowired
	private MealPlanService mealPlanService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<MealPlan>> fetchAll() {
		try {
			List<MealPlan> mealPlans = mealPlanService.findAll();
			return new ResponseEntity<List<MealPlan>>(mealPlans, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/{mealPlanId}")
	public ResponseEntity<MealPlan> getMealPlanById(@PathVariable("mealPlanId") Long mealPlanId) {
		try {
			MealPlan mealPlan = mealPlanService.findById(mealPlanId);
			if (mealPlan != null) {
				return new ResponseEntity<MealPlan>(mealPlan, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "{mealPlanId}")
	public ResponseEntity<?> updateMealPlan(@PathVariable("mealPlanId") Long mealPlanId, @RequestBody MealPlan mealPlan)
			throws Exception {
		MealPlan currentMealPlan = mealPlanService.update(mealPlan, mealPlanId);
		return ResponseEntity.ok(currentMealPlan);
	}

	@DeleteMapping(value = "{mealPlanId}")
	public void deleteMealPlan(@PathVariable("mealPlanId") Long mealPlanId) throws Exception {
		mealPlanService.deleteById(mealPlanId);
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
