package com.myhealth.Dto.Requests;

import lombok.Data;

@Data
public class PatientDtoRequest {

    private Long profileId;

    private Double height;

    private Double weight;

    private Long emergencyPhone;
}
