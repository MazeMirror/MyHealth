package com.myhealth.Controllers;

import java.util.Date;
import java.util.List;

import com.myhealth.Dto.Requests.DailyGoalDtoRequest;
import com.myhealth.Dto.Requests.PatientDtoRequest;
import com.myhealth.Dto.Requests.SpecialistDtoRequest;
import com.myhealth.Dto.Requests.WeeklyGoalDtoRequest;
import com.myhealth.Dto.Responses.DailyGoalDtoResponse;
import com.myhealth.Dto.Responses.PatientDtoResponse;
import com.myhealth.Dto.Responses.SpecialistDtoResponse;
import com.myhealth.Dto.Responses.WeeklyGoalDtoResponse;
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
import org.springframework.web.bind.annotation.*;

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

	@PutMapping(path ="{patientId}")
	public ResponseEntity<PatientDtoResponse> putPatient(@PathVariable("patientId") Long patientId, @RequestBody PatientDtoRequest patientDtoRequest) throws RuntimeException {
		//List<UserDtoResponse> users = userService.getUsers();
		PatientDtoResponse patient = patientService.putPatient(patientId,patientDtoRequest);
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}

	@PostMapping(path = "{patientId}/mealPlans")
	public ResponseEntity<MealPlan> createMealPlan(@PathVariable("patientId") Long patientId,
			@RequestBody MealPlan mealPlan) {

		MealPlan mealPlan1 = mealPlanService.createMealPlan(patientId, mealPlan);
		return ResponseEntity.status(HttpStatus.CREATED).body(mealPlan1);
	}

	/*@PostMapping(path = "{patientId}/weeklyGoal")
	public ResponseEntity<WeeklyGoal> createWeeklyGoal(@PathVariable("patientId") Long patientId,
			@RequestBody WeeklyGoal weeklyGoal) {

		WeeklyGoal weeklyGoal1 = weeklyGoalService.createWeeklyGoal(patientId, weeklyGoal);
		return ResponseEntity.status(HttpStatus.CREATED).body(weeklyGoal1);
	}*/

	@PostMapping(path = "{patientId}/dailyGoal")
	public ResponseEntity<DailyGoalDtoResponse> createDailyGoal(@PathVariable("patientId") Long patientId,
			@RequestBody DailyGoalDtoRequest dailyGoal) {

		DailyGoalDtoResponse dailyGoalDto = dailyGoalService.createDailyGoal(patientId, dailyGoal);
		return ResponseEntity.status(HttpStatus.CREATED).body(dailyGoalDto);
	}

	/*@GetMapping(path = "{patientId}/dailyGoals")
	public ResponseEntity<List<DailyGoalDtoResponse>> getDailyGoalsByPatientId(@PathVariable("patientId") Long patientId, @RequestParam(value = "activityId",required = false) Long activityId) {

		List<DailyGoalDtoResponse> dailyGoals;
		if(activityId == null){
			dailyGoals = dailyGoalService.getDailyGoalsByPatientId(patientId);
		}
		else{
			dailyGoals = dailyGoalService.getDailyGoalsByPatientIdAndActivityId(patientId,activityId);
		}
		return new ResponseEntity<>(dailyGoals, HttpStatus.OK);
	}*/
	@GetMapping(path = "{patientId}/dailyGoals")
	public ResponseEntity<List<DailyGoalDtoResponse>> getDailyGoalsByPatientId(@PathVariable("patientId") Long patientId, @RequestParam(value = "date",required = false) Date date) {

		List<DailyGoalDtoResponse> dailyGoals;
		if(date == null){
			dailyGoals = dailyGoalService.getDailyGoalsByPatientId(patientId);
		}
		else{
			dailyGoals = dailyGoalService.getDailyGoalsByPatientIdAndDate(patientId,date);
		}
		return new ResponseEntity<>(dailyGoals, HttpStatus.OK);
	}

	@PostMapping(path = "{patientId}/weeklyGoal")
	public ResponseEntity<WeeklyGoalDtoResponse> createWeeklyGoal(@PathVariable("patientId") Long patientId,
																  @RequestBody WeeklyGoalDtoRequest weeklyGoalDtoRequest) {

		WeeklyGoalDtoResponse weeklyGoalDto = weeklyGoalService.createWeeklyGoal(patientId, weeklyGoalDtoRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(weeklyGoalDto);
	}

	@GetMapping(path = "{patientId}/weeklyGoals")
	public ResponseEntity<List<WeeklyGoalDtoResponse>> getWeeklyGoalsByPatientId(@PathVariable("patientId") Long patientId, @RequestParam(value = "activityId",required = false) Long activityId) {

		List<WeeklyGoalDtoResponse> weeklyGoals;
		if(activityId == null){
			weeklyGoals = weeklyGoalService.getWeeklyGoalByPatientId(patientId);
		}
		else{
			weeklyGoals = weeklyGoalService.getWeeklyGoalsByPatientIdAndActivityId(patientId,activityId);
		}
		return new ResponseEntity<>(weeklyGoals, HttpStatus.OK);
	}
}
