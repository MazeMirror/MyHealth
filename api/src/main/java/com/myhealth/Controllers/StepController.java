package com.myhealth.Controllers;

import com.myhealth.Dto.Requests.StepDtoRequest;
import com.myhealth.Dto.Responses.StepDtoResponse;
import com.myhealth.Entities.DailyGoal;
import com.myhealth.Services.DailyGoalService;
import com.myhealth.Services.ProfileService;
import com.myhealth.Services.StepService;
import com.myhealth.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/step")
public class StepController {

    @Autowired
    private StepService stepService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<StepDtoResponse>> fetchAll() {
        try {
            List<StepDtoResponse> steps = stepService.findAll();
            return new ResponseEntity<List<StepDtoResponse>>(steps, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StepDtoResponse> getStepById(@PathVariable("id") Long id) {
        try {
            StepDtoResponse step = stepService.findById(id);
            if (step != null) {
                return new ResponseEntity<StepDtoResponse>(step, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<StepDtoResponse> updateStep(@PathVariable("id") Long id,
                                             @RequestBody StepDtoRequest stepDtoRequest) throws Exception {
        StepDtoResponse stepDtoResponse = stepService.update(id,stepDtoRequest);
        return ResponseEntity.ok(stepDtoResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> DeleteStep(@PathVariable("id") Long id) throws Exception {
        stepService.deleteById(id);
        return new ResponseEntity<>("Deleted step" + id, HttpStatus.OK);
    }





}
