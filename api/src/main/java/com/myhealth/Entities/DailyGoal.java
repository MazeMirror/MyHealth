package com.myhealth.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "daily_goal")
@NoArgsConstructor
public class DailyGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="activity")
    private String activity;

    @Column(name="quantity")
    private Long quantity;

    @Column(name="progress")
    private Long progress;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
}
