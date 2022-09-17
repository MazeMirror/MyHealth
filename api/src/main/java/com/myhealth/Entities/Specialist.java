package com.myhealth.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myhealth.Dto.Requests.SpecialistDtoRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "specialist")
@NoArgsConstructor
public class Specialist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "profile_id", nullable = true)
	@JsonIgnore
	private Profile profile;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "specialists_patients", joinColumns = {
			@JoinColumn(name = "specialist_id") }, inverseJoinColumns = { @JoinColumn(name = "patient_id") })
	private List<Patient> patients;

	@Column(name = "specialty", length = 64, nullable = false)
	private String specialty;

	public Specialist(Profile profile, SpecialistDtoRequest specialistDtoRequest) {
		this.profile = profile;
		this.specialty = specialistDtoRequest.getSpecialty();
	}

	public List<Patient> getPatientSpecialists() {
		return patients;
	}

	public boolean hasPatient(Patient patient) {
		return this.getPatientSpecialists().contains(patient);
	}

	public Specialist assignWith(Patient patient) {
		if (!this.hasPatient(patient))
			this.getPatientSpecialists().add(patient);
		return this;
	}

	public Specialist unassignWith(Patient patient) {
		if (this.hasPatient(patient))
			this.getPatientSpecialists().remove(patient);
		return this;
	}

}
