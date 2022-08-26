package com.myhealth.Controllers;

import com.myhealth.Dto.Requests.UserDtoRequest;
import com.myhealth.Dto.Responses.UserDtoResponse;
import com.myhealth.Entities.User;
import com.myhealth.Services.UserService;

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
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("{id}")
	public ResponseEntity<UserDtoResponse> getUser(@PathVariable Long id) throws RuntimeException {
		UserDtoResponse user = userService.getUser(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UserDtoResponse> postUser(@RequestBody UserDtoRequest userDtoRequest)
			throws RuntimeException {
		UserDtoResponse user = userService.postUser(userDtoRequest);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
