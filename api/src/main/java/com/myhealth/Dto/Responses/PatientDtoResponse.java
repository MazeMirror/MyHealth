package com.myhealth.Dto.Responses;

import lombok.Data;


@Data
public class PatientDtoResponse {

    private Long id;

    private Long profileId;

    private Long height;

    private Long weight;

    private String emergencyPhone;
}
