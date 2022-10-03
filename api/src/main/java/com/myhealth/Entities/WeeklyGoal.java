package com.myhealth.Entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myhealth.Dto.Requests.DailyGoalDtoRequest;
import com.myhealth.Dto.Requests.WeeklyGoalDtoRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "weekly_goal")
@NoArgsConstructor
public class WeeklyGoal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "quantity")
	private double quantity;

	@Column(name = "progress")
	private double progress;

	@Temporal(TemporalType.DATE)
	@Column(name = "startDate", nullable = false)
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "endDate", nullable = false)
	private Date endDate;

	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	@JsonIgnore
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "activity_id", nullable = false)
	@JsonIgnore
	private Activity activity;

	public WeeklyGoal(Patient patient, Activity activity, WeeklyGoalDtoRequest weeklyGoalDtoRequest) {
		this.patient = patient;
		this.activity = activity;
		this.progress = weeklyGoalDtoRequest.getProgress();
		this.quantity = weeklyGoalDtoRequest.getQuantity();
		this.startDate = weeklyGoalDtoRequest.getStartDate();
		this.endDate = weeklyGoalDtoRequest.getEndDate();
	}
}
