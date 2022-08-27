package com.myhealth.Services;

import java.util.List;

import com.myhealth.Entities.WeeklyGoal;
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

	public WeeklyGoal createWeeklyGoal(Long patientId, WeeklyGoal weeklyGoal) {
		return patientRepository.findById(patientId).map(
				patient -> {
					weeklyGoal.setPatient(patient);
					return weeklyGoalRepository.save(weeklyGoal);
				}).orElseThrow(() -> new RuntimeException("patient not found"));
	}
}
