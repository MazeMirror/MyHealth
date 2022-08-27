package com.myhealth.Services;

import com.myhealth.Entities.DailyGoal;
import com.myhealth.Entities.MealPlan;
import com.myhealth.Repositories.DailyGoalRepository;
import com.myhealth.Repositories.MealPlanRepository;
import com.myhealth.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyGoalService {

    @Autowired
    private DailyGoalRepository dailyGoalRepository;

    @Autowired
    private PatientRepository patientRepository;

    public List<DailyGoal> findAll() throws Exception{
        return dailyGoalRepository.findAll();
    }

    public DailyGoal findById(Long aLong) throws Exception {
        DailyGoal dailyGoal = dailyGoalRepository.findById(aLong).orElse(null);

        return dailyGoal;
    }


    public DailyGoal update(DailyGoal entity, Long aLong) throws Exception {
        DailyGoal dailyGoal = dailyGoalRepository.findById(aLong)
                .orElseThrow(()->new RuntimeException("daily goal not found"));
        dailyGoal.setActivity(entity.getActivity());
        dailyGoal.setQuantity(entity.getQuantity());
        dailyGoal.setProgress(entity.getProgress());

        return dailyGoalRepository.save(dailyGoal);
    }

    public void deleteById(Long aLong) throws Exception {
        DailyGoal dailyGoal = dailyGoalRepository.findById(aLong)
                .orElseThrow(()->new RuntimeException("daily goal not found"));
        dailyGoalRepository.deleteById(aLong);
    }

    public DailyGoal createDailyGoal(Long patientId,DailyGoal dailyGoal) {

        return patientRepository.findById(patientId).map(
                patient -> {dailyGoal.setPatient(patient);
                    return dailyGoalRepository.save(dailyGoal);}
        ).orElseThrow(()->new RuntimeException("patient not found"));
    }
}
