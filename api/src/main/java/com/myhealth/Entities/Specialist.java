package com.myhealth.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.myhealth.Dto.Requests.SpecialistDtoRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

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

	@Column(name = "specialty", length = 64, nullable = false)
	private String specialty;

	public Specialist(Profile profile, SpecialistDtoRequest specialistDtoRequest) {
		this.profile = profile;
		this.specialty = specialistDtoRequest.getSpecialty();
	}

}
