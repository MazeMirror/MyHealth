package com.myhealth.Entities;

import javax.persistence.*;

import com.myhealth.Dto.Requests.DailyGoalDtoRequest;
import com.myhealth.Dto.Requests.SpecialistDtoRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false)
	private Date date;
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
		this.date = dailyGoalDtoRequest.getDate();
	}
}
