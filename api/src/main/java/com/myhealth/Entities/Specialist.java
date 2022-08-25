package com.myhealth.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "specialist")
public class Specialist {
	@Id
	private Long id;

	@OneToOne
	@JoinColumn(name = "profile_id", referencedColumnName = "id")
	private Profile profile;

	@Column(name = "specialty", length = 64, nullable = false)
	private String specialty;

}
