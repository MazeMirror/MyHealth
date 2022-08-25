package com.myhealth.Services;

import javax.transaction.Transactional;

import com.myhealth.Common.EntityDtoConverter;
import com.myhealth.Dto.RoleDtoRequest;
import com.myhealth.Dto.RoleDtoResponse;
import com.myhealth.Entities.Role;
import com.myhealth.Repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private EntityDtoConverter entityDtoConverter;

	public Role saveRole(RoleDtoRequest roleDto) {
		return roleRepository.save(new Role(roleDto));
	}

	public RoleDtoResponse getRole(Long id) {
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("role not found"));

		return entityDtoConverter.convertRoleToDto(role);

	}

}
