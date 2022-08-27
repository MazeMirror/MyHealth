package com.myhealth.Controllers;

import com.myhealth.Dto.Requests.ProfileDtoRequest;
import com.myhealth.Dto.Responses.ProfileDtoResponse;
import com.myhealth.Services.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	ProfileService profileService;

	@GetMapping("{id}")
	public ResponseEntity<ProfileDtoResponse> getProfile(@PathVariable Long id) throws RuntimeException {
		ProfileDtoResponse profile = profileService.getProfile(id);
		return new ResponseEntity<>(profile, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ProfileDtoResponse> postSpecialist(@RequestBody ProfileDtoRequest profileDtoRequest)
			throws RuntimeException {
		ProfileDtoResponse profile = profileService.postProfile(profileDtoRequest);
		return new ResponseEntity<>(profile, HttpStatus.OK);
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
