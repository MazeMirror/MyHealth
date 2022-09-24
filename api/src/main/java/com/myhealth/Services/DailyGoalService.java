package com.myhealth.Services;

import java.util.Date;
import java.util.List;

import com.myhealth.Common.EntityDtoConverter;
import com.myhealth.Dto.Requests.DailyGoalDtoRequest;
import com.myhealth.Dto.Responses.DailyGoalDtoResponse;
import com.myhealth.Entities.DailyGoal;
import com.myhealth.Repositories.ActivityRepository;
import com.myhealth.Repositories.DailyGoalRepository;
import com.myhealth.Repositories.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailyGoalService {

	@Autowired
	private DailyGoalRepository dailyGoalRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	EntityDtoConverter entityDtoConverter;

	public List<DailyGoal> findAll() throws Exception {
		return dailyGoalRepository.findAll();
	}

	public DailyGoal findById(Long aLong) throws Exception {
		DailyGoal dailyGoal = dailyGoalRepository.findById(aLong).orElse(null);

		return dailyGoal;
	}

	public DailyGoal update(DailyGoal entity, Long aLong) throws Exception {
		DailyGoal dailyGoal = dailyGoalRepository.findById(aLong)
				.orElseThrow(() -> new RuntimeException("daily goal not found"));
		dailyGoal.setActivity(entity.getActivity());
		dailyGoal.setQuantity(entity.getQuantity());
		dailyGoal.setProgress(entity.getProgress());

		return dailyGoalRepository.save(dailyGoal);
	}

	public void deleteById(Long aLong) throws Exception {
		DailyGoal dailyGoal = dailyGoalRepository.findById(aLong)
				.orElseThrow(() -> new RuntimeException("daily goal not found"));
		dailyGoalRepository.deleteById(aLong);
	}

	public DailyGoalDtoResponse createDailyGoal(Long patientId, DailyGoalDtoRequest dailyGoalDtoRequest) {
		var patient = patientRepository.findById(patientId).orElseThrow(() -> new RuntimeException("patient not found by Id "+patientId));
		var activity = activityRepository.findById(dailyGoalDtoRequest.getActivityId()).orElseThrow(() -> new RuntimeException("activity not found by Id "+dailyGoalDtoRequest.getActivityId()));

		var response = dailyGoalRepository.save(new DailyGoal(patient,activity,dailyGoalDtoRequest));
		return entityDtoConverter.convertDailyGoalToDto(response);
	}

    public List<DailyGoalDtoResponse> getDailyGoalsByPatientId(Long patientId) {
		List<DailyGoal> dailyGoals = dailyGoalRepository.getDailyGoalsByPatientId(patientId);
		return entityDtoConverter.convertDailyGoalsToDto(dailyGoals);
    }

	/*public List<DailyGoalDtoResponse> getDailyGoalsByPatientIdAndActivityId(Long patientId, Long activityId) {
		List<DailyGoal> dailyGoals = dailyGoalRepository.getDailyGoalsByPatientIdAndActivityId(patientId,activityId);
		return entityDtoConverter.convertDailyGoalsToDto(dailyGoals);
	}*/

	public List<DailyGoalDtoResponse> getDailyGoalsByPatientIdAndDate(Long patientId, Date date) {
		List<DailyGoal> dailyGoals = dailyGoalRepository.getDailyGoalsByPatientIdAndDateEquals(patientId,date);
		return entityDtoConverter.convertDailyGoalsToDto(dailyGoals);
	}
}
