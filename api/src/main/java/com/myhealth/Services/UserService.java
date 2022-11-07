package com.myhealth.Services;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import com.myhealth.Common.EntityDtoConverter;
import com.myhealth.Dto.Requests.UserDtoRequest;
import com.myhealth.Dto.Responses.UserDtoResponse;
import com.myhealth.Entities.DailyGoal;
import com.myhealth.Entities.Profile;
import com.myhealth.Entities.User;
import com.myhealth.Repositories.ProfileRepository;
import com.myhealth.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	EntityDtoConverter entityDtoConverter;

	@Autowired
	ProfileRepository profileRepository;

	public UserDtoResponse getUser(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("user not found"));
		return entityDtoConverter.convertUserToDto(user);
	}

	public UserDtoResponse postUser(UserDtoRequest userDtoRequest) {
		User user = userRepository.save(new User(userDtoRequest));
		return entityDtoConverter.convertUserToDto(user);
	}

	public UserDtoResponse authenticateUser(UserDtoRequest userDtoRequest) {
		User user = null;
		UserDtoRequest userDtoRequest1 = new UserDtoRequest();
		userDtoRequest1.setEmail("NoEmail");
		userDtoRequest1.setPassword("NoPassword");
		user = userRepository.findByEmail(userDtoRequest.getEmail())
				.orElseThrow(() -> new RuntimeException("user specified not found"));
		if (user != null) {
			userDtoRequest1.setEmail(user.getEmail());
			if (user.getPassword().equals( userDtoRequest.getPassword())) {
				return entityDtoConverter.convertUserToDto(user);
			}
			userDtoRequest1.setEmail("NoPassword");
		}

		User aux = new User(userDtoRequest1);
		if(Objects.equals(userDtoRequest1.getEmail(), "NoPassword")){
			aux.setId(-1L);
		}
		return entityDtoConverter.convertUserToDto(aux);
	}

	public List<UserDtoResponse> getUsers() {
		List<User> users = userRepository.findAll();
		return entityDtoConverter.convertUsersToDto(users);
	}

    public UserDtoResponse putUserEmail(Long userId, UserDtoRequest userDtoRequest) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("user specified not found"));

		var updatedUser = new User();
		updatedUser.setId(userId);
		updatedUser.setEmail(userDtoRequest.getEmail());
		updatedUser.setPassword(user.getPassword());
		var dto = userRepository.save(updatedUser);

		return entityDtoConverter.convertUserToDto(dto);
    }

	public void deleteById(Long aLong) throws Exception {
		User user = userRepository.findById(aLong)
				.orElseThrow(() -> new RuntimeException("user not found"));
		userRepository.deleteById(aLong);
	}
}
