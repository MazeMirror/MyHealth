package com.myhealth.Dto.Requests;

import lombok.Data;

@Data
public class DailyGoalDtoRequest {

    private Double quantity;

    private Double progress;

    private Long activityId;
}
