package com.myhealth.Controllers;

import com.myhealth.Dto.Requests.SpecialistDtoRequest;
import com.myhealth.Dto.Responses.SpecialistDtoResponse;
import com.myhealth.Entities.Specialist;
import com.myhealth.Services.SpecialistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/specialist")
public class SpecialistController {

	@Autowired
	SpecialistService specialistService;

	@GetMapping("{id}")
	public ResponseEntity<SpecialistDtoResponse> getSpecialist(@PathVariable Long id) throws RuntimeException {
		SpecialistDtoResponse specialist = specialistService.getSpecialist(id);
		return new ResponseEntity<>(specialist, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<SpecialistDtoResponse> postSpecialist(@RequestBody SpecialistDtoRequest specialistDtoRequest)
			throws RuntimeException {
		SpecialistDtoResponse specialist = specialistService.postSpecialist(specialistDtoRequest);
		return new ResponseEntity<>(specialist, HttpStatus.OK);
	}

}
