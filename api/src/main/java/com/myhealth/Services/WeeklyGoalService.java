package com.myhealth.Services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.myhealth.Common.EntityDtoConverter;
import com.myhealth.Dto.Requests.WeeklyGoalDtoRequest;
import com.myhealth.Dto.Responses.WeeklyGoalDtoResponse;
import com.myhealth.Entities.WeeklyGoal;
import com.myhealth.Repositories.ActivityRepository;
import com.myhealth.Repositories.PatientRepository;
import com.myhealth.Repositories.WeeklyGoalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeeklyGoalService {

	@Autowired
	private WeeklyGoalRepository weeklyGoalRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	EntityDtoConverter entityDtoConverter;

	public List<WeeklyGoal> findAll() throws Exception {
		return weeklyGoalRepository.findAll();
	}

	public WeeklyGoal findById(Long aLong) throws Exception {
		WeeklyGoal weeklyGoal = weeklyGoalRepository.findById(aLong).orElse(null);

		return weeklyGoal;
	}

	public WeeklyGoal update(WeeklyGoal entity, Long aLong) throws Exception {
		WeeklyGoal weeklyGoal = weeklyGoalRepository.findById(aLong)
				.orElseThrow(() -> new RuntimeException("weekly goal not found"));
		weeklyGoal.setActivity(entity.getActivity());
		weeklyGoal.setQuantity(entity.getQuantity());
		weeklyGoal.setProgress(entity.getProgress());

		return weeklyGoalRepository.save(weeklyGoal);
	}

	public void deleteById(Long aLong) throws Exception {
		WeeklyGoal weeklyGoal = weeklyGoalRepository.findById(aLong)
				.orElseThrow(() -> new RuntimeException("weekly goal not found"));
		weeklyGoalRepository.deleteById(aLong);
	}

	/*public WeeklyGoal createWeeklyGoal(Long patientId, WeeklyGoal weeklyGoal) {
		return patientRepository.findById(patientId).map(
				patient -> {
					weeklyGoal.setPatient(patient);
					return weeklyGoalRepository.save(weeklyGoal);
				}).orElseThrow(() -> new RuntimeException("patient not found"));
	}*/

	public List<WeeklyGoalDtoResponse> getWeeklyGoalByPatientId(Long patientId) {
		List<WeeklyGoal> weeklyGoals = weeklyGoalRepository.getWeeklyGoalsByPatientId(patientId);
		return entityDtoConverter.convertWeeklyGoalsToDto(weeklyGoals);
	}

	/*public List<WeeklyGoalDtoResponse> getWeeklyGoalsByPatientIdAndActivityId(Long patientId, Long activityId) {
		List<WeeklyGoal> weeklyGoals = weeklyGoalRepository.getWeeklyGoalsByPatientIdAndActivityId(patientId,activityId);
		return entityDtoConverter.convertWeeklyGoalsToDto(weeklyGoals);
	}*/

	public WeeklyGoalDtoResponse createWeeklyGoal(Long patientId, WeeklyGoalDtoRequest weeklyGoalDtoRequest) {
		var patient = patientRepository.findById(patientId).orElseThrow(() -> new RuntimeException("patient not found by Id "+patientId));
		var activity = activityRepository.findById(weeklyGoalDtoRequest.getActivityId()).orElseThrow(() -> new RuntimeException("activity not found by Id "+weeklyGoalDtoRequest.getActivityId()));

		var response = weeklyGoalRepository.save(new WeeklyGoal(patient,activity,weeklyGoalDtoRequest));
		return entityDtoConverter.convertWeeklyGoalToDto(response);
	}

    public List<WeeklyGoalDtoResponse> getWeeklyGoalByPatientIdAndFilteredByDates(Long patientId, Date startDate, Date endDate) {
		List<WeeklyGoal> tempWeeklyGoals = weeklyGoalRepository.getWeeklyGoalsByPatientIdOrderByQuantity(patientId);

		var filteredWg = tempWeeklyGoals.stream().filter(
				weeklyGoal -> (weeklyGoal.getStartDate().after(startDate) || weeklyGoal.getStartDate().equals(startDate))
								&& (weeklyGoal.getEndDate().before(endDate) || weeklyGoal.getEndDate().equals(endDate))
		).toList();

		return entityDtoConverter.convertWeeklyGoalsToDto(filteredWg);
    }

	public WeeklyGoalDtoResponse updateWeeklyGoalByPatientIdAndId(Long patientId, Long weeklyGoalId, WeeklyGoalDtoRequest weeklyGoalDtoRequest) {
		var patient = patientRepository.findById(patientId).orElseThrow(() -> new RuntimeException("patient not found by Id "+patientId));
		var activity = activityRepository.findById(weeklyGoalDtoRequest.getActivityId()).orElseThrow(() -> new RuntimeException("activity not found by Id "+weeklyGoalDtoRequest.getActivityId()));
		var weeklyGoal = weeklyGoalRepository.findById(weeklyGoalId).orElseThrow(() -> new RuntimeException("dailyGoal not found by Id "+weeklyGoalId));

		weeklyGoal.setQuantity(weeklyGoalDtoRequest.getQuantity());
		weeklyGoal.setProgress(weeklyGoalDtoRequest.getProgress());
		weeklyGoal.setStartDate(weeklyGoalDtoRequest.getStartDate());
		weeklyGoal.setEndDate(weeklyGoalDtoRequest.getEndDate());

		var response = weeklyGoalRepository.save(weeklyGoal);
		return entityDtoConverter.convertWeeklyGoalToDto(response);
	}
}
