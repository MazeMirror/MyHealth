package com.myhealth.Controllers;

import com.myhealth.Dto.Requests.DistanceDtoRequest;
import com.myhealth.Dto.Requests.KilocalorieDtoRequest;
import com.myhealth.Dto.Responses.DistanceDtoResponse;
import com.myhealth.Dto.Responses.KilocalorieDtoResponse;
import com.myhealth.Services.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/distance")
public class DistanceController {

    @Autowired
    private DistanceService distanceService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<DistanceDtoResponse>> fetchAll() {
        try {
            List<DistanceDtoResponse> distances = distanceService.findAll();
            return new ResponseEntity<List<DistanceDtoResponse>>(distances, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DistanceDtoResponse> getDistanceById(@PathVariable("id") Long id) {
        try {
            DistanceDtoResponse distanceDtoResponse = distanceService.findById(id);
            if (distanceDtoResponse != null) {
                return new ResponseEntity<DistanceDtoResponse>(distanceDtoResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<DistanceDtoResponse> updateDistance(@PathVariable("id") Long id,
                                                                    @RequestBody DistanceDtoRequest distanceDtoRequest) throws Exception {
        DistanceDtoResponse distanceDtoResponse = distanceService.update(id,distanceDtoRequest);
        return ResponseEntity.ok(distanceDtoResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> DeleteDistance(@PathVariable("id") Long id) throws Exception {
        distanceService.deleteById(id);
        return new ResponseEntity<>("Deleted distance " + id, HttpStatus.OK);
    }
}
