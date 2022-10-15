package com.myhealth.Controllers;

import com.myhealth.Dto.Requests.KilocalorieDtoRequest;
import com.myhealth.Dto.Requests.StepDtoRequest;
import com.myhealth.Dto.Responses.KilocalorieDtoResponse;
import com.myhealth.Dto.Responses.StepDtoResponse;
import com.myhealth.Services.DailyGoalService;
import com.myhealth.Services.KilocalorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kilocalorie")
public class KilocalorieController {

    @Autowired
    private KilocalorieService kilocalorieService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<KilocalorieDtoResponse>> fetchAll() {
        try {
            List<KilocalorieDtoResponse> kilocalories = kilocalorieService.findAll();
            return new ResponseEntity<List<KilocalorieDtoResponse>>(kilocalories, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<KilocalorieDtoResponse> getKilocalorieById(@PathVariable("id") Long id) {
        try {
            KilocalorieDtoResponse kilocalorie = kilocalorieService.findById(id);
            if (kilocalorie != null) {
                return new ResponseEntity<KilocalorieDtoResponse>(kilocalorie, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<KilocalorieDtoResponse> updateKilocalorie(@PathVariable("id") Long id,
                                                                    @RequestBody KilocalorieDtoRequest kilocalorieDtoRequest) throws Exception {
        KilocalorieDtoResponse kilocalorieDtoResponse = kilocalorieService.update(id,kilocalorieDtoRequest);
        return ResponseEntity.ok(kilocalorieDtoResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> DeleteKilocalorie(@PathVariable("id") Long id) throws Exception {
        kilocalorieService.deleteById(id);
        return new ResponseEntity<>("Deleted kilocalorie" + id, HttpStatus.OK);
    }
}
