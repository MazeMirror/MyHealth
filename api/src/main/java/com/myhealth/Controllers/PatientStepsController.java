package com.myhealth.Controllers;

import com.myhealth.Dto.Requests.StepDtoRequest;
import com.myhealth.Dto.Responses.MealPlanDtoResponse;
import com.myhealth.Dto.Responses.StepDtoResponse;
import com.myhealth.Services.PatientService;
import com.myhealth.Services.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/patient-steps")
public class PatientStepsController {

    @Autowired
    private StepService stepService;

    @Autowired
    private PatientService patientService;

    @GetMapping("{patientId}/steps")
    public ResponseEntity<List<StepDtoResponse>> getStepsByPatientId(@PathVariable("patientId") Long patientId,
                                                                     @RequestParam(value = "startDate", required = false) Date startDate,
                                                                     @RequestParam(value = "endDate", required = false) Date endDate)
    {
        List<StepDtoResponse> stepDtoResponseList;
        if(startDate == null && endDate == null){
            stepDtoResponseList = stepService.getStepsByPatientId(patientId);
        }else{
            stepDtoResponseList = stepService.getStepsByPatientIdAndFilteredByDates(patientId,startDate,endDate);
        }

        return new ResponseEntity<>(stepDtoResponseList, HttpStatus.OK);
    }

    @GetMapping("{patientId}/step/{stepId}")
    public ResponseEntity<StepDtoResponse> getStepByPatientIdAndDate(@PathVariable("patientId") Long patientId,
                                                                     @PathVariable("stepId") Long stepId,
                                                                     @RequestParam(value = "date") Date date) {
        try{
            StepDtoResponse stepDtoResponse = stepService.findByIdAndPatientIdWithDateEqualsTo(stepId,patientId, date);
            if (stepDtoResponse != null) {
                return new ResponseEntity<StepDtoResponse>(stepDtoResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("{patientId}/step")
    public ResponseEntity<StepDtoResponse> postStepByPatientId(@PathVariable("patientId") Long patientId,
                                                               @RequestBody StepDtoRequest stepDtoRequest){
        StepDtoResponse stepDtoResponse = stepService.createStep(patientId,stepDtoRequest);

        return new ResponseEntity<>(stepDtoResponse, HttpStatus.OK);
    }

    @PutMapping("{patientId}/step/{stepId}")
    public ResponseEntity<StepDtoResponse> putStepByIdAndPatientId(@PathVariable("patientId") Long patientId,
                                                                   @PathVariable("stepId") Long stepId,
                                                                   @RequestBody StepDtoRequest stepDtoRequest)
    {

        StepDtoResponse stepDtoResponse = stepService.updateByIdAndPatientId(stepId,patientId, stepDtoRequest);

        return new ResponseEntity<>(stepDtoResponse, HttpStatus.OK);
    }

}
