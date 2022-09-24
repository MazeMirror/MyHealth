package com.myhealth.Dto.Responses;

import lombok.Data;

import java.util.Date;

@Data
public class WeeklyGoalDtoResponse {
    private Long id;

    private Double quantity;

    private Double progress;

    private Long patientId;

    private Long activityId;
}
