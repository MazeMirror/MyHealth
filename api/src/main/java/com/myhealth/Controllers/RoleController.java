package com.myhealth.Controllers;

import com.myhealth.Dto.RoleDto;
import com.myhealth.Repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	RoleRepository roleRepository;

	@GetMapping
	public ResponseEntity<RoleDto> getRole(@PathVariable Long id) {
		return null;

	}

}
