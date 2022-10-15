package com.myhealth.Controllers;

import java.util.Date;
import java.util.List;

import com.myhealth.Dto.Requests.*;
import com.myhealth.Dto.Responses.*;
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

	@PutMapping(path = "{patientId}/dailyGoal/{dailyGoalId}")
	public ResponseEntity<DailyGoalDtoResponse> updateDailyGoal(@PathVariable("patientId") Long patientId,
																	@PathVariable("dailyGoalId") Long dailyGoalId,
																	@RequestBody DailyGoalDtoRequest dailyGoal) {

		DailyGoalDtoResponse dailyGoalDto = dailyGoalService.updateDailyGoalByPatientIdAndId(patientId, dailyGoalId,dailyGoal);
		return new ResponseEntity<>(dailyGoalDto, HttpStatus.OK);
	}

	@PutMapping(path = "{patientId}/dailyGoal/{dailyGoalId}/step")
	public ResponseEntity<DailyGoalDtoResponse> updateDailyGoalStep(@PathVariable("patientId") Long patientId,
																@PathVariable("dailyGoalId") Long dailyGoalId,
																@RequestBody DailyGoalDtoRequest dailyGoal) {

		DailyGoalDtoResponse dailyGoalDto = dailyGoalService.updateDailyGoalByPatientIdAndIdOfSteps(patientId, dailyGoalId,dailyGoal);
		return new ResponseEntity<>(dailyGoalDto, HttpStatus.OK);
	}

	@PutMapping(path = "{patientId}/dailyGoal/{dailyGoalId}/distance")
	public ResponseEntity<DailyGoalDtoResponse> updateDailyGoalDistance(@PathVariable("patientId") Long patientId,
																@PathVariable("dailyGoalId") Long dailyGoalId,
																@RequestBody DailyGoalDtoRequest dailyGoal) {

		DailyGoalDtoResponse dailyGoalDto = dailyGoalService.updateDailyGoalByPatientIdAndIdOfDistance(patientId, dailyGoalId,dailyGoal);
		return new ResponseEntity<>(dailyGoalDto, HttpStatus.OK);
	}

	@PutMapping(path = "{patientId}/dailyGoal/{dailyGoalId}/kilocalorie")
	public ResponseEntity<DailyGoalDtoResponse> updateDailyGoalKilocalorie(@PathVariable("patientId") Long patientId,
																		@PathVariable("dailyGoalId") Long dailyGoalId,
																		@RequestBody DailyGoalDtoRequest dailyGoal) {

		DailyGoalDtoResponse dailyGoalDto = dailyGoalService.updateDailyGoalByPatientIdAndIdOfKilocalorie(patientId, dailyGoalId,dailyGoal);
		return new ResponseEntity<>(dailyGoalDto, HttpStatus.OK);
	}

	//Actulizar weeklyGoal
	@PutMapping(path = "{patientId}/weeklyGoalId/{weeklyGoalId}")
	public ResponseEntity<WeeklyGoalDtoResponse> updateWeeklyGoal(@PathVariable("patientId") Long patientId,
																@PathVariable("weeklyGoalId") Long weeklyGoalId,
																@RequestBody WeeklyGoalDtoRequest weeklyGoal) {

		WeeklyGoalDtoResponse weeklyGoalDto = weeklyGoalService.updateWeeklyGoalByPatientIdAndId(patientId, weeklyGoalId,weeklyGoal);
		return new ResponseEntity<>(weeklyGoalDto, HttpStatus.OK);
	}

	//Eliminar dailyGoal
	@DeleteMapping(path="{patientId}/dailyGoalId/{dailyGoalId}")
	public void deleteDailyGoal(@PathVariable("dailyGoalId") Long dailyGoalId) throws Exception{
		dailyGoalService.deleteById(dailyGoalId);
	}
	//Eliminar weeklyGoal
	@DeleteMapping(path="{patientId}/weeklyGoalId/{weeklyGoalId}")
	public void deleteWeeklyGoal(@PathVariable("weeklyGoalId") Long weeklyGoalId) throws Exception{
		weeklyGoalService.deleteById(weeklyGoalId);
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

	@GetMapping(path = "{patientId}/dailyGoals/filter",params = {"startDate","endDate"})
	public ResponseEntity<List<DailyGoalDtoResponse>> getDailyGoalsByPatientIdAndDates(@PathVariable("patientId") Long patientId,
																					   @RequestParam(value = "startDate") Date startDate,
																					   @RequestParam(value = "endDate") Date endDate)
	{

		List<DailyGoalDtoResponse> dailyGoals = dailyGoalService.getDailyGoalsByPatientIdAndFilteredByDates(patientId,startDate,endDate);

		return new ResponseEntity<>(dailyGoals, HttpStatus.OK);
	}

	@PostMapping(path = "{patientId}/weeklyGoal")
	public ResponseEntity<WeeklyGoalDtoResponse> createWeeklyGoal(@PathVariable("patientId") Long patientId,
																  @RequestBody WeeklyGoalDtoRequest weeklyGoalDtoRequest) {

		WeeklyGoalDtoResponse weeklyGoalDto = weeklyGoalService.createWeeklyGoal(patientId, weeklyGoalDtoRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(weeklyGoalDto);
	}

	/*@GetMapping(path = "{patientId}/weeklyGoals")
	public ResponseEntity<List<WeeklyGoalDtoResponse>> getWeeklyGoalsByPatientId(@PathVariable("patientId") Long patientId, @RequestParam(value = "activityId",required = false) Long activityId) {

		List<WeeklyGoalDtoResponse> weeklyGoals;
		if(activityId == null){
			weeklyGoals = weeklyGoalService.getWeeklyGoalByPatientId(patientId);
		}
		else{
			weeklyGoals = weeklyGoalService.getWeeklyGoalsByPatientIdAndActivityId(patientId,activityId);
		}
		return new ResponseEntity<>(weeklyGoals, HttpStatus.OK);
	}*/

	@GetMapping(path = "{patientId}/weeklyGoals")
	public ResponseEntity<List<WeeklyGoalDtoResponse>> getWeeklyGoalsByPatientId(@PathVariable("patientId") Long patientId,
																				 @RequestParam(value = "startDate", required = false) Date startDate,
																				 @RequestParam(value = "endDate", required = false) Date endDate ) {

		List<WeeklyGoalDtoResponse> weeklyGoals;

		if(startDate == null && endDate == null){
			weeklyGoals = weeklyGoalService.getWeeklyGoalByPatientId(patientId);
		}else{
			weeklyGoals = weeklyGoalService.getWeeklyGoalByPatientIdAndFilteredByDates(patientId, startDate,endDate);
		}

		/*if(activityId == null){
			weeklyGoals = weeklyGoalService.getWeeklyGoalByPatientId(patientId);
		}
		else{
			weeklyGoals = weeklyGoalService.getWeeklyGoalsByPatientIdAndActivityId(patientId,activityId);
		}*/
		return new ResponseEntity<>(weeklyGoals, HttpStatus.OK);
	}

	//Obtener los MealPlan
	@GetMapping(path = "{patientId}/mealPlans")
	public ResponseEntity<List<MealPlanDtoResponse>> getMealPlansByPatiendId(@PathVariable("patientId") Long patientId){

		List<MealPlanDtoResponse> mealPlans;
		mealPlans = mealPlanService.getMealPlansByPatientId(patientId);

		return new ResponseEntity<>(mealPlans, HttpStatus.OK);
	}

	//Actualizar MealPlan
	@PutMapping(path = "{patientId}/mealPlans/{mealPlanId}")
	public ResponseEntity<MealPlanDtoResponse> updateMealPlan(@PathVariable("patientId") Long patientId,
															  @PathVariable("mealPlanId") Long mealPlanId,
															  @RequestBody MealPlanDtoRequest mealPlan){

		MealPlanDtoResponse mealPlanDto = mealPlanService.updateMealPlanByPatientIdAndId(patientId, mealPlanId, mealPlan);
		return new ResponseEntity<>(mealPlanDto, HttpStatus.OK);
	}

	//Eliminar mealPlan
	@DeleteMapping(path="{patientId}/mealPlans/{mealPlanId}")
	public void deleteMealPlan(@PathVariable("mealPlanId") Long mealPlanId) throws Exception{
		mealPlanService.deleteById(mealPlanId);
	}

	@DeleteMapping(path="{patientId}")
	public void deletePatient(@PathVariable("patientId") Long patientId) throws Exception{
		patientService.deleteById(patientId);
	}
}
