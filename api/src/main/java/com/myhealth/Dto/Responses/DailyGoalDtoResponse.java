package com.myhealth.Dto.Responses;

import lombok.Data;

@Data
public class DailyGoalDtoResponse {
    private Long id;

    private Double quantity;

    private Double progress;

    private Long patientId;

    private Long activityId;
}
