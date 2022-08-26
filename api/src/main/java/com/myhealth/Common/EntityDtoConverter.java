package com.myhealth.Common;

import com.myhealth.Dto.Responses.ProfileDtoResponse;
import com.myhealth.Dto.Responses.RoleDtoResponse;
import com.myhealth.Dto.Responses.SpecialistDtoResponse;
import com.myhealth.Dto.Responses.UserDtoResponse;
import com.myhealth.Entities.Profile;
import com.myhealth.Entities.Role;
import com.myhealth.Entities.Specialist;
import com.myhealth.Entities.User;

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

	public UserDtoResponse convertUserToDto(User user) {
		return modelMapper.map(user, UserDtoResponse.class);
	}

	public ProfileDtoResponse convertProfileToDto(Profile profile) {
		return modelMapper.map(profile, ProfileDtoResponse.class);
	}

	public SpecialistDtoResponse convertSpecialistToDto(Specialist specialist) {
		return modelMapper.map(specialist, SpecialistDtoResponse.class);
	}

}
