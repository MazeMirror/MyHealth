package com.myhealth.Services;

import javax.transaction.Transactional;

import com.myhealth.Common.EntityDtoConverter;
import com.myhealth.Dto.Requests.SpecialistDtoRequest;
import com.myhealth.Dto.Responses.SpecialistDtoResponse;

import com.myhealth.Entities.Patient;
import com.myhealth.Entities.Profile;
import com.myhealth.Entities.Specialist;
import com.myhealth.Repositories.PatientRepository;

import com.myhealth.Entities.Profile;
import com.myhealth.Entities.Specialist;

import com.myhealth.Repositories.ProfileRepository;
import com.myhealth.Repositories.SpecialistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.stereotype.Service;

@Transactional
@Service
public class SpecialistService {
	@Autowired
	SpecialistRepository specialistRepository;

	@Autowired
	ProfileRepository profileRepository;

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	EntityDtoConverter entityDtoConverter;

	public SpecialistDtoResponse getSpecialist(Long id) {
		Specialist specialist = specialistRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Specialist not found"));
		return entityDtoConverter.convertSpecialistToDto(specialist);
	}

	public SpecialistDtoResponse postSpecialist(SpecialistDtoRequest specialistDtoRequest) {
		Profile profile = profileRepository.findById(specialistDtoRequest.getProfileId())
				.orElseThrow(() -> new RuntimeException("Profile id specified not found"));
		Specialist specialist = new Specialist(profile, specialistDtoRequest);
		Specialist specialist2 = specialistRepository.save(specialist);
		return entityDtoConverter.convertSpecialistToDto(specialist2);
	}

	public Specialist assignSpecialistPatient(Long specialistId, Long patientId) {
		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(()->new RuntimeException("Patient not found"));
		return specialistRepository.findById(specialistId).map(
						specialist -> specialistRepository.save(specialist.assignWith(patient)))
				.orElseThrow(()->new RuntimeException("Specialist not found"));
	}

	public Specialist unAssignSpecialistPatient(Long specialistId, Long patientId) {
		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(()->new RuntimeException("Patient not found"));
		return specialistRepository.findById(specialistId).map(
						specialist -> specialistRepository.save(specialist.unassignWith(patient)))
				.orElseThrow(()->new RuntimeException("Specialist not found"));
	}

	public List<Patient> getAllPatientsBySpecialistId(Long specialistId) {
		return specialistRepository.findById(specialistId).map(
				specialist -> {
					List<Patient> patients = specialist.getPatients();
					return patients;
				}).orElseThrow(() -> new RuntimeException("Specialist not found"));
	}
}
