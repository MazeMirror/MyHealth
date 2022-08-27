package com.myhealth.Entities;

import com.myhealth.Dto.Requests.SpecialistDtoRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "patient")
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "profile_id", nullable = false)
//    private Profile profile;

    @OneToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id", unique = true)
    private Profile profile;

    @Column(name="height")
    private Long height;

    @Column(name="weight")
    private Long weight;

    @Column(name="emergency_phone")
    private String emergencyPhone;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE}
            , mappedBy = "patients")
    private List<Specialist> specialistsPatients;

    public Patient(Profile profile) {
        this.profile = profile;
    }

}
