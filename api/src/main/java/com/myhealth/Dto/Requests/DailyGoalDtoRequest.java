package com.myhealth.Dto.Requests;

import lombok.Data;

import java.util.Date;

@Data
public class DailyGoalDtoRequest {

    private Double quantity;

    private Double progress;

    private Date date;

    private Long activityId;
}
