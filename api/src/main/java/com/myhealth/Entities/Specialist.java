package com.myhealth.Entities;

import javax.persistence.*;

import com.myhealth.Dto.Requests.SpecialistDtoRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "specialist")
@NoArgsConstructor
public class Specialist {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	@JoinColumn(name = "profile_id", referencedColumnName = "id", unique = true)
	private Profile profile;

	@ManyToMany(fetch = FetchType.LAZY,
			cascade ={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name = "specialists_patients",
			joinColumns = {@JoinColumn(name = "specialist_id")},
			inverseJoinColumns = {@JoinColumn(name = "patient_id")})
	private List<Patient> patients;

	@Column(name = "specialty", length = 64, nullable = false)
	private String specialty;

	public Specialist(Profile profile, SpecialistDtoRequest specialistDtoRequest) {
		this.profile = profile;
		this.specialty = specialistDtoRequest.getSpecialty();
	}

	public List<Patient> getPatientSpecialists(){return patients;}

	public boolean hasPatient(Patient patient){return this.getPatientSpecialists().contains(patient);}
	public Specialist assignWith(Patient patient){
		if(!this.hasPatient(patient))
			this.getPatientSpecialists().add(patient);
		return this;
	}

	public Specialist unassignWith(Patient patient){
		if(this.hasPatient(patient))
			this.getPatientSpecialists().remove(patient);
		return this;
	}

}
