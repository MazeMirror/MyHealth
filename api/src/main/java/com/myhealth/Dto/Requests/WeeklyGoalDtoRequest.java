package com.myhealth.Dto.Requests;

import lombok.Data;

import java.util.Date;

@Data
public class WeeklyGoalDtoRequest {
    private Double quantity;

    private Double progress;

    private Long activityId;
}
