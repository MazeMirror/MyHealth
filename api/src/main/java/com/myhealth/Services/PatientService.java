package com.myhealth.Services;
import com.myhealth.Common.EntityDtoConverter;

import java.util.List;

import com.myhealth.Dto.Requests.PatientDtoRequest;
import com.myhealth.Dto.Responses.PatientDtoResponse;
import com.myhealth.Entities.Patient;
import com.myhealth.Repositories.PatientRepository;
import com.myhealth.Repositories.ProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	EntityDtoConverter entityDtoConverter;

	public List<PatientDtoResponse> findAll() throws Exception {
		return patientRepository.findAll().stream().map(patient -> entityDtoConverter.convertPatientToDto(patient)).toList();
	}

	public Patient createPatient(Patient patient, Long profileId) {

		return profileRepository.findById(profileId).map(
				profile -> {
					patient.setProfile(profile);
					return patientRepository.save(patient);
				}).orElseThrow(() -> new RuntimeException("profile not found"));
	}

	//getPatientByProfileId

	public PatientDtoResponse getPatientByProfileId(Long profileId) {
		Patient patient = patientRepository.findByProfileId(profileId)
				.orElseThrow(() -> new RuntimeException("Profile not found"));
		return entityDtoConverter.convertPatientToDto(patient);
	}

    public PatientDtoResponse putPatient(Long patientId, PatientDtoRequest patientDtoRequest) {
		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(() -> new RuntimeException("Profile not found"));

		patient.setHeight(patientDtoRequest.getHeight());
		patient.setWeight(patientDtoRequest.getWeight());
		patient.setEmergencyPhone(patientDtoRequest.getEmergencyPhone());

		var dto = patientRepository.save(patient);

		return entityDtoConverter.convertPatientToDto(dto);
    }
}
