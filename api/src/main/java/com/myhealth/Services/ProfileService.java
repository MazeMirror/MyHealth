package com.myhealth.Services;

import java.util.List;

import javax.transaction.Transactional;

import com.myhealth.Common.EntityDtoConverter;
import com.myhealth.Dto.Requests.ProfileDtoRequest;
import com.myhealth.Dto.Responses.ProfileDtoResponse;
import com.myhealth.Entities.Profile;
import com.myhealth.Entities.Role;
import com.myhealth.Entities.User;
import com.myhealth.Repositories.PatientRepository;
import com.myhealth.Repositories.ProfileRepository;
import com.myhealth.Repositories.RoleRepository;
import com.myhealth.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private EntityDtoConverter entityDtoConverter;

	public ProfileDtoResponse getProfile(Long id) {
		Profile profile = profileRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("profile not found"));
		return entityDtoConverter.convertProfileToDto(profile);
	}

	public ProfileDtoResponse getProfileByUserId(Long id) {
		Profile profile = profileRepository.findByUserId(id)
				.orElseThrow(() -> new RuntimeException("profile not found by UserId"));
		return entityDtoConverter.convertProfileToDto(profile);
	}

	public ProfileDtoResponse postProfile(ProfileDtoRequest profileDtoRequest) {
		User user = userRepository.findById(profileDtoRequest.getUserId())
				.orElseThrow(() -> new RuntimeException("user specified not found"));
		Role role = roleRepository.findById(profileDtoRequest.getRoleId())
				.orElseThrow(() -> new RuntimeException("role specified not found"));
		Profile profile = profileRepository.save(new Profile(role, user, profileDtoRequest));
		return entityDtoConverter.convertProfileToDto(profile);
	}

	public Boolean deleteProfile(Long id) {
		if (profileRepository.existsById(id)) {
			profileRepository.deleteById(id);
			return true;
		} else {
			return false;
		}

	}

	public List<ProfileDtoResponse> getProfiles() {
		List<Profile> profiles = profileRepository.findAll();
		return entityDtoConverter.convertProfilesToDto(profiles);
	}

    public ProfileDtoResponse put(Long id, ProfileDtoRequest profileDtoRequest) {
		User user = userRepository.findById(profileDtoRequest.getUserId())
				.orElseThrow(() -> new RuntimeException("user specified not found"));
		Role role = roleRepository.findById(profileDtoRequest.getRoleId())
				.orElseThrow(() -> new RuntimeException("role specified not found"));
		Profile profile = profileRepository.findByUserId(id)
				.orElseThrow(() -> new RuntimeException("profile not found by UserId"));

		var newProfile = new Profile(role, user, profileDtoRequest);
		newProfile.setId(id);
		var updatedProfile = profileRepository.save(newProfile);
		return entityDtoConverter.convertProfileToDto(updatedProfile);
    }

	public List<ProfileDtoResponse> getProfilesByNameAndRoleId(String name, long roleId) {
		List<Profile> profilesByNameAndRoleId = profileRepository.findProfileByNameContainingAndRoleId(name,roleId);
		//Then
		//var profilesByRoleId = profilesByName.stream().filter(profile -> profile.getRole().getId() == roleId).toList();
		return entityDtoConverter.convertProfilesToDto(profilesByNameAndRoleId);
	}

	public List<ProfileDtoResponse> getProfilesRoleId(long roleId) {
		List<Profile> profilesByRole = profileRepository.findProfilesByRoleId(roleId);
		//Then
		return entityDtoConverter.convertProfilesToDto(profilesByRole);
	}

    public List<ProfileDtoResponse> getProfilesByNameAndRoleIdAndSpecialistId(String name, long roleId, long specialistId) {
		List<Profile> profilesByNameAndRoleId = profileRepository.findProfileByNameContainingAndRoleId(name,roleId);

		var profilesBySpecialistId = profilesByNameAndRoleId.stream().filter(profile -> {
			var patient = patientRepository.findByProfileId(profile.getId());

			if(patient.isPresent()){
				var specialistList = patient.get().getSpecialistsPatients();

				var existSpecialist = specialistList.stream().filter(specialist -> specialist.getId() == specialistId).toList();

				if(existSpecialist.isEmpty()){
					return false;
				}else{
					return true;
				}


			}else return false;

		}).toList();

		return entityDtoConverter.convertProfilesToDto(profilesBySpecialistId);
    }
}
