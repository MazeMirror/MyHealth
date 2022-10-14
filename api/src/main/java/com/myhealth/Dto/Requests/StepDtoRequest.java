package com.myhealth.Dto.Requests;

import lombok.Data;

import java.util.Date;

@Data
public class StepDtoRequest {
    private Double quantity;

    private Date date;
}
