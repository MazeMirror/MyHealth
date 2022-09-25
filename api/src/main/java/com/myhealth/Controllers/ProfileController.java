package com.myhealth.Controllers;

import java.util.List;

import com.myhealth.Dto.Requests.ProfileDtoRequest;
import com.myhealth.Dto.Responses.PatientDtoResponse;
import com.myhealth.Dto.Responses.ProfileDtoResponse;

import com.myhealth.Dto.Responses.SpecialistDtoResponse;
import com.myhealth.Entities.Patient;
import com.myhealth.Services.PatientService;

import com.myhealth.Services.ProfileService;

import com.myhealth.Services.SpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	ProfileService profileService;

	@Autowired
	PatientService patientService;

	@Autowired
	SpecialistService specialistService;

	/*@GetMapping
	public ResponseEntity<List<ProfileDtoResponse>> getProfiles() throws RuntimeException {
		List<ProfileDtoResponse> profiles = profileService.getProfiles();
		return new ResponseEntity<>(profiles, HttpStatus.OK);
	}*/

	@GetMapping
	public ResponseEntity<List<ProfileDtoResponse>> getProfilesByNameAndRoleId(@RequestParam String name,@RequestParam long roleId) throws RuntimeException {
		List<ProfileDtoResponse> profiles = profileService.getProfilesByNameAndRoleId(name,roleId);
		return new ResponseEntity<>(profiles, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<ProfileDtoResponse>> getProfilesByRoleId(@RequestParam long roleId) throws RuntimeException {
		List<ProfileDtoResponse> profiles = profileService.getProfilesRoleId(roleId);
		return new ResponseEntity<>(profiles, HttpStatus.OK);
	}

	@PutMapping("{id}")
	public ResponseEntity<ProfileDtoResponse> putProfile(@PathVariable Long id,@RequestBody ProfileDtoRequest profileDtoRequest) throws RuntimeException {
		ProfileDtoResponse profile = profileService.put(id,profileDtoRequest);
		return new ResponseEntity<>(profile, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<ProfileDtoResponse> getProfile(@PathVariable Long id) throws RuntimeException {
		ProfileDtoResponse profile = profileService.getProfile(id);
		return new ResponseEntity<>(profile, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ProfileDtoResponse> postProfile(@RequestBody ProfileDtoRequest profileDtoRequest)
			throws RuntimeException {
		ProfileDtoResponse profile = profileService.postProfile(profileDtoRequest);
		return new ResponseEntity<>(profile, HttpStatus.OK);
	}

	@PostMapping(path = "{profileId}/patient")
	public ResponseEntity<Patient> postPatient(@RequestBody Patient patient,
			@PathVariable("profileId") Long profileId) {

		Patient patient1 = patientService.createPatient(patient, profileId);
		return ResponseEntity.status(HttpStatus.CREATED).body(patient1);
	}

	@GetMapping(path = "{profileId}/patient")
	public ResponseEntity<PatientDtoResponse> getPatientByProfileId(@PathVariable("profileId") Long profileId) {

		PatientDtoResponse patient = patientService.getPatientByProfileId(profileId);
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}

	@GetMapping(path = "{profileId}/specialist")
	public ResponseEntity<SpecialistDtoResponse> getSpecialistByProfileId(@PathVariable("profileId") Long profileId) {

		SpecialistDtoResponse specialist = specialistService.getSpecialistByProfileId(profileId);
		return new ResponseEntity<>(specialist, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteSpecialist(@PathVariable Long id) {
		if (!profileService.deleteProfile(id)) {
			return new ResponseEntity<>(id + " couldn't be deleted", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(id + " deleted succesfully", HttpStatus.ACCEPTED);
		}
	}

}
