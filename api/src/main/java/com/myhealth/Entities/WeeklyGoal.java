package com.myhealth.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "weekly_goal")
@NoArgsConstructor
public class WeeklyGoal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "activity")
	private String activity;

	@Column(name = "quantity")
	private Long quantity;

	@Column(name = "progress")
	private Long progress;

	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;

}
