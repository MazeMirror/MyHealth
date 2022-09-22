package com.myhealth.Entities;

import com.myhealth.Dto.Requests.RoleDtoRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "activity")
@NoArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "activity")
    private Set<DailyGoal> dailyGoals;

    @OneToMany(mappedBy = "activity")
    private Set<WeeklyGoal> weeklyGoals;
}
