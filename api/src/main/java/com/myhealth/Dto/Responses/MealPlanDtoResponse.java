package com.myhealth.Dto.Responses;

import lombok.Data;

@Data
public class MealPlanDtoResponse {

    private Long id;

    private String name;

    private String description;

    private Long patientId;
}
