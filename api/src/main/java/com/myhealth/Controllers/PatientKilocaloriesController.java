package com.myhealth.Controllers;

import com.myhealth.Dto.Requests.KilocalorieDtoRequest;
import com.myhealth.Dto.Responses.KilocalorieDtoResponse;
import com.myhealth.Services.KilocalorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/patient-kilocalories")
public class PatientKilocaloriesController {

    @Autowired
    private KilocalorieService kilocalorieService;

    @GetMapping("{patientId}/kilocalories")
    public ResponseEntity<List<KilocalorieDtoResponse>> getStepsByPatientId(@PathVariable("patientId") Long patientId,
                                                                            @RequestParam(value = "startDate", required = false) Date startDate,
                                                                            @RequestParam(value = "endDate", required = false) Date endDate)
    {
        List<KilocalorieDtoResponse> kilocalorieDtoResponseList;
        if(startDate == null && endDate == null){
            kilocalorieDtoResponseList = kilocalorieService.getKilocaloriesByPatientId(patientId);
        }else{
            kilocalorieDtoResponseList = kilocalorieService.getKilocaloriesByPatientIdAndFilteredByDates(patientId,startDate,endDate);
        }

        return new ResponseEntity<>(kilocalorieDtoResponseList, HttpStatus.OK);
    }

    @GetMapping("{patientId}/kilocalorie/{kilocalorieId}")
    public ResponseEntity<KilocalorieDtoResponse> getKilocalorieByPatientIdAndDate(@PathVariable("patientId") Long patientId,
                                                                     @PathVariable("kilocalorieId") Long stepId,
                                                                     @RequestParam(value = "date") Date date) {
        try{
            KilocalorieDtoResponse kilocalorieDtoResponse = kilocalorieService.findByIdAndPatientIdWithDateEqualsTo(stepId,patientId, date);
            if (kilocalorieDtoResponse != null) {
                return new ResponseEntity<KilocalorieDtoResponse>(kilocalorieDtoResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("{patientId}/kilocalorie")
    public ResponseEntity<KilocalorieDtoResponse> postKilocalorieByPatientId(@PathVariable("patientId") Long patientId,
                                                               @RequestBody KilocalorieDtoRequest kilocalorieDtoRequest){
        KilocalorieDtoResponse kilocalorieDtoResponse = kilocalorieService.createKilocalorie(patientId,kilocalorieDtoRequest);

        return new ResponseEntity<>(kilocalorieDtoResponse, HttpStatus.OK);
    }

    @PutMapping("{patientId}/kilocalorie/{kilocalorieId}")
    public ResponseEntity<KilocalorieDtoResponse> putKilocalorieByIdAndPatientId(@PathVariable("patientId") Long patientId,
                                                                   @PathVariable("kilocalorieId") Long kilocalorieId,
                                                                   @RequestBody KilocalorieDtoRequest kilocalorieDtoRequest)
    {

        KilocalorieDtoResponse kilocalorieDtoResponse = kilocalorieService.updateByIdAndPatientId(kilocalorieId,patientId, kilocalorieDtoRequest);

        return new ResponseEntity<>(kilocalorieDtoResponse, HttpStatus.OK);
    }
}
