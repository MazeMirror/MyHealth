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
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "patient")
@NoArgsConstructor
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// @ManyToOne
	// @JoinColumn(name = "profile_id", nullable = false)
	// private Profile profile;

	@OneToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "profile_id",nullable = true)
	@JsonIgnore
	private Profile profile;

	@Column(name = "height")
	private Double height;

	@Column(name = "weight")
	private Double weight;

	@Column(name = "emergency_phone")
	private Long emergencyPhone;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "patients")
	@JsonIgnore
	private List<Specialist> specialistsPatients;

//	public Patient(Profile profile) {
//		this.profile = profile;
//	}

}
