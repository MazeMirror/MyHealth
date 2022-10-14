package com.myhealth.Services;

import com.myhealth.Common.EntityDtoConverter;
import com.myhealth.Dto.Requests.DailyGoalDtoRequest;
import com.myhealth.Dto.Requests.StepDtoRequest;
import com.myhealth.Dto.Responses.DailyGoalDtoResponse;
import com.myhealth.Dto.Responses.StepDtoResponse;
import com.myhealth.Entities.DailyGoal;
import com.myhealth.Entities.Step;
import com.myhealth.Repositories.PatientRepository;
import com.myhealth.Repositories.StepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StepService {
    @Autowired
    private StepRepository stepRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    EntityDtoConverter entityDtoConverter;

    public List<StepDtoResponse> findAll() throws Exception {
        var stepList = stepRepository.findAll();
        return entityDtoConverter.convertStepsToDto(stepList);
    }

    public StepDtoResponse findById(Long id) throws Exception {
        Step step = stepRepository.findById(id).orElseThrow(() -> new RuntimeException("step not found"));

        return entityDtoConverter.convertStepToDto(step);
    }

    public StepDtoResponse findByIdAndPatientIdWithDateEqualsTo(Long id,long patientId,Date date)  {
        Step step = stepRepository.findByIdAndPatientIdAndDateEquals(id,patientId,date).orElseThrow(() -> new RuntimeException("step not found"));;

        return entityDtoConverter.convertStepToDto(step);
    }

    public List<StepDtoResponse> getStepsByPatientId(Long patientId) {
        List<Step> steps = stepRepository.getStepsByPatientIdOrderByQuantityAsc(patientId);
        return entityDtoConverter.convertStepsToDto(steps);
    }

    public List<StepDtoResponse> getStepsByPatientIdAndFilteredByDates(Long patientId, Date startDate, Date endDate) {
        List<Step> tempSteps = stepRepository.getStepsByPatientIdOrderByQuantityAsc(patientId);

        var filteredStep = tempSteps.stream().filter(
                step -> ( (step.getDate().after(startDate) || (step.getDate().equals(startDate))) &&
                        (step.getDate().before(endDate ) || step.getDate().equals(endDate)) )
        ).toList();

        return entityDtoConverter.convertStepsToDto(filteredStep);
    }




    public StepDtoResponse createStep(Long patientId, StepDtoRequest stepDtoRequest) {
        var patient = patientRepository.findById(patientId).orElseThrow(() -> new RuntimeException("patient not found by Id "+patientId));

        var stepEntity = new Step();
        stepEntity.setDate(stepDtoRequest.getDate());
        stepEntity.setQuantity(stepDtoRequest.getQuantity());
        stepEntity.setPatient(patient);

        var response = stepRepository.save(stepEntity);

        //var response = dailyGoalRepository.save(new DailyGoal(patient,activity,dailyGoalDtoRequest));
        return entityDtoConverter.convertStepToDto(response);
    }

    public StepDtoResponse update(Long id, StepDtoRequest stepDtoRequest) throws Exception {
        Step step = stepRepository.findById(id).orElseThrow(() -> new RuntimeException("step not found"));

        step.setQuantity(stepDtoRequest.getQuantity());
        step.setDate(stepDtoRequest.getDate());

        var response = stepRepository.save(step);

        return entityDtoConverter.convertStepToDto(response);
    }

    public StepDtoResponse updateByIdAndPatientId(Long id, Long patientId,StepDtoRequest stepDtoRequest) {
        var patient = patientRepository.findById(patientId).orElseThrow(() -> new RuntimeException("patient not found by Id"));

        Step step = stepRepository.findById(id).orElseThrow(() -> new RuntimeException("step not found by Id"));

        step.setQuantity(stepDtoRequest.getQuantity());
        step.setDate(stepDtoRequest.getDate());

        var response = stepRepository.save(step);

        return entityDtoConverter.convertStepToDto(response);
    }

    public void deleteById(Long id) throws Exception {
        Step step = stepRepository.findById(id).orElseThrow(() -> new RuntimeException("step not found by Id"));

        stepRepository.deleteById(id);
    }





}
