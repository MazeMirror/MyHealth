package com.myhealth.Common;

import com.myhealth.Dto.RoleDtoResponse;
import com.myhealth.Entities.Role;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoConverter {
	private final ModelMapper modelMapper;

	public EntityDtoConverter(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public RoleDtoResponse convertRoleToDto(Role role) {
		return modelMapper.map(role, RoleDtoResponse.class);

	}

}
