package com.myhealth.Dto.Responses;

import lombok.Data;

import java.util.Date;

@Data
public class DailyGoalDtoResponse {
    private Long id;

    private Double quantity;

    private Double progress;

    private Date date;

    private Long patientId;

    private Long activityId;
}
