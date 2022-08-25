package com.myhealth.Controllers;

import com.myhealth.Dto.RoleDtoRequest;
import com.myhealth.Dto.RoleDtoResponse;
import com.myhealth.Entities.Role;
import com.myhealth.Services.RoleService;

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
@RequestMapping("/role")
public class RoleController {

	@Autowired
	RoleService roleService;

	@GetMapping("{id}")
	public ResponseEntity<RoleDtoResponse> getRole(@PathVariable Long id) throws RuntimeException {
		RoleDtoResponse roleDto = roleService.getRole(id);
		return new ResponseEntity<>(roleDto, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Role> getRole(@RequestBody RoleDtoRequest role)
			throws RuntimeException {
		return new ResponseEntity<>(roleService.saveRole(role), HttpStatus.OK);
	}

}
