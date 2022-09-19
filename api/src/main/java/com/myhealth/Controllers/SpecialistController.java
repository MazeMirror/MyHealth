package com.myhealth.Controllers;

import java.util.List;

import com.myhealth.Dto.Requests.SpecialistDtoRequest;
import com.myhealth.Dto.Requests.UserDtoRequest;
import com.myhealth.Dto.Responses.PatientDtoResponse;
import com.myhealth.Dto.Responses.SpecialistDtoResponse;
import com.myhealth.Dto.Responses.UserDtoResponse;
import com.myhealth.Entities.Specialist;
import com.myhealth.Services.SpecialistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/specialist")
public class SpecialistController {

	@Autowired
	SpecialistService specialistService;

	@GetMapping
	public ResponseEntity<List<SpecialistDtoResponse>> getSpecialists() throws RuntimeException {
		List<SpecialistDtoResponse> specialists = specialistService.getSpecialists();
		return new ResponseEntity<>(specialists, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<SpecialistDtoResponse> getSpecialist(@PathVariable Long id) throws RuntimeException {
		SpecialistDtoResponse specialist = specialistService.getSpecialist(id);
		return new ResponseEntity<>(specialist, HttpStatus.OK);
	}

	@PutMapping(path ="{specialistId}")
	public ResponseEntity<SpecialistDtoResponse> putSpecialistSpecialty(@PathVariable("specialistId") Long specialistId, @RequestBody SpecialistDtoRequest specialistDtoRequest) throws RuntimeException {
		//List<UserDtoResponse> users = userService.getUsers();
		SpecialistDtoResponse specialist = specialistService.putSpecialty(specialistId,specialistDtoRequest);
		return new ResponseEntity<>(specialist, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<SpecialistDtoResponse> postSpecialist(@RequestBody SpecialistDtoRequest specialistDtoRequest)
			throws RuntimeException {
		SpecialistDtoResponse specialist = specialistService.postSpecialist(specialistDtoRequest);
		return new ResponseEntity<>(specialist, HttpStatus.OK);
	}

	@PostMapping("{specialistId}/patients/{patientId}")
	public ResponseEntity<Specialist> assignSpecialistPatient(@PathVariable Long specialistId,
			@PathVariable Long patientId) {
		Specialist specialist = specialistService.assignSpecialistPatient(specialistId, patientId);
		return new ResponseEntity<>(specialist, HttpStatus.OK);

	}

	@DeleteMapping("{specialistId}/patients/{patientId}")
	public ResponseEntity<Specialist> unassignSpecialistPatient(@PathVariable Long specialistId,
			@PathVariable Long patientId) {
		Specialist specialist = specialistService.unAssignSpecialistPatient(specialistId, patientId);
		return new ResponseEntity<>(specialist, HttpStatus.OK);

	}

	@GetMapping("/{specialistId}/patients")
	public ResponseEntity<List<PatientDtoResponse>> getAllPatientsBySpecialistId(@PathVariable Long specialistId) {
		try {
			List<PatientDtoResponse> patients = specialistService.getAllPatientsBySpecialistId(specialistId);
			return new ResponseEntity<>(patients, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//
	// @PostMapping("{specialistId}/patients/{patientId}")
	// public ResponseEntity<Specialist> unassignSpecialistPatient(@PathVariable
	// Long specialistId,@PathVariable Long patientId){
	// Specialist specialist =
	// specialistService.unAssignSpecialistPatient(specialistId, patientId);
	// return ResponseEntity.ok(specialist);
	//
	// }

}
