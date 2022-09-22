package com.myhealth.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.myhealth.Dto.Requests.DailyGoalDtoRequest;
import com.myhealth.Dto.Requests.SpecialistDtoRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "daily_goal")
@NoArgsConstructor
public class DailyGoal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "quantity")
	private double quantity;

	@Column(name = "progress")
	private double progress;

	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "activity_id", nullable = false)
	private Activity activity;

	public DailyGoal(Patient patient, Activity activity,DailyGoalDtoRequest dailyGoalDtoRequest) {
		this.patient = patient;
		this.activity = activity;
		this.progress = dailyGoalDtoRequest.getProgress();
		this.quantity = dailyGoalDtoRequest.getQuantity();
	}
}
