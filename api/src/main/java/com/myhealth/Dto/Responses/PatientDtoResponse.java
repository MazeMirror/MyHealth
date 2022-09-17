package com.myhealth.Dto.Responses;

import lombok.Data;


@Data
public class PatientDtoResponse {

    private Long id;

    private Long profileId;

    private Double height;

    private Double weight;

    private Long emergencyPhone;
}
