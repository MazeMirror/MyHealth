package com.myhealth.Controllers;

import com.myhealth.Dto.Requests.DistanceDtoRequest;
import com.myhealth.Dto.Requests.KilocalorieDtoRequest;
import com.myhealth.Dto.Responses.DistanceDtoResponse;
import com.myhealth.Dto.Responses.KilocalorieDtoResponse;
import com.myhealth.Services.DistanceService;
import com.myhealth.Services.KilocalorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/patient-distances")
public class PatientDistancesController {

    @Autowired
    private DistanceService distanceService;

    @GetMapping("{patientId}/distances")
    public ResponseEntity<List<DistanceDtoResponse>> getDistancesByPatientId(@PathVariable("patientId") Long patientId,
                                                                         @RequestParam(value = "startDate", required = false) Date startDate,
                                                                         @RequestParam(value = "endDate", required = false) Date endDate)
    {
        List<DistanceDtoResponse> distanceDtoResponseList;
        if(startDate == null && endDate == null){
            distanceDtoResponseList = distanceService.getDistancesByPatientId(patientId);
        }else{
            distanceDtoResponseList = distanceService.getDistancesByPatientIdAndFilteredByDates(patientId,startDate,endDate);
        }

        return new ResponseEntity<>(distanceDtoResponseList, HttpStatus.OK);
    }

    @GetMapping("{patientId}/distance/{distanceId}")
    public ResponseEntity<DistanceDtoResponse> getDistanceByPatientIdAndDate(@PathVariable("patientId") Long patientId,
                                                                                   @PathVariable("distanceId") Long stepId,
                                                                                   @RequestParam(value = "date") Date date) {
        try{
            DistanceDtoResponse distanceDtoResponse = distanceService.findByIdAndPatientIdWithDateEqualsTo(stepId,patientId, date);
            if (distanceDtoResponse != null) {
                return new ResponseEntity<DistanceDtoResponse>(distanceDtoResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("{patientId}/distance")
    public ResponseEntity<DistanceDtoResponse> postKilocalorieByPatientId(@PathVariable("patientId") Long patientId,
                                                                             @RequestBody DistanceDtoRequest distanceDtoRequest){
        DistanceDtoResponse distanceDtoResponse = distanceService.createDistance(patientId,distanceDtoRequest);

        return new ResponseEntity<>(distanceDtoResponse, HttpStatus.OK);
    }

    @PutMapping("{patientId}/distance/{distanceId}")
    public ResponseEntity<DistanceDtoResponse> putDistanceByIdAndPatientId(@PathVariable("patientId") Long patientId,
                                                                                 @PathVariable("distanceId") Long distanceId,
                                                                                 @RequestBody DistanceDtoRequest distanceDtoRequest)
    {

        DistanceDtoResponse distanceDtoResponse = distanceService.updateByIdAndPatientId(distanceId,patientId, distanceDtoRequest);

        return new ResponseEntity<>(distanceDtoResponse, HttpStatus.OK);
    }
}
