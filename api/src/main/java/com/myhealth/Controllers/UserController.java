package com.myhealth.Controllers;

import java.util.List;

import com.myhealth.Dto.Requests.ProfileDtoRequest;
import com.myhealth.Dto.Requests.UserDtoRequest;
import com.myhealth.Dto.Responses.ProfileDtoResponse;
import com.myhealth.Dto.Responses.UserDtoResponse;
import com.myhealth.Services.ProfileService;
import com.myhealth.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	ProfileService profileService;

	@GetMapping
	public ResponseEntity<List<UserDtoResponse>> getUsers() throws RuntimeException {
		List<UserDtoResponse> users = userService.getUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping(path ="{userId}/profile")
	public ResponseEntity<ProfileDtoResponse> getProfile(@PathVariable("userId") Long userId) throws RuntimeException {
		//List<UserDtoResponse> users = userService.getUsers();
		ProfileDtoResponse profile = profileService.getProfileByUserId(userId);
		return new ResponseEntity<>(profile, HttpStatus.OK);
	}

	@PutMapping(path ="{userId}")
	public ResponseEntity<UserDtoResponse> putUserEmail(@PathVariable("userId") Long userId,@RequestBody UserDtoRequest userDtoRequest) throws RuntimeException {
		//List<UserDtoResponse> users = userService.getUsers();
		UserDtoResponse user = userService.putUserEmail(userId,userDtoRequest);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<UserDtoResponse> getUser(@PathVariable Long id) throws RuntimeException {
		UserDtoResponse user = userService.getUser(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<UserDtoResponse> authenticateUser(@RequestBody UserDtoRequest userDtoRequest)
			throws RuntimeException {
		UserDtoResponse user = userService.authenticateUser(userDtoRequest);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UserDtoResponse> postUser(@RequestBody UserDtoRequest userDtoRequest)
			throws RuntimeException {
		UserDtoResponse user = userService.postUser(userDtoRequest);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
