package com.myhealth.Dto.Responses;

import lombok.Data;

import java.util.Date;

@Data
public class DistanceDtoResponse {
    private Long id;

    private Double quantity;

    private Date date;

    private Long patientId;
}
