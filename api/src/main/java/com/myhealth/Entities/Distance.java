package com.myhealth.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "distance")
@NoArgsConstructor
public class Distance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="quantity")
    private Long quantity;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    Date date;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
}
