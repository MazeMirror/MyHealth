package com.myhealth.Dto.Requests;

import lombok.Data;

import java.util.Date;

@Data
public class DistanceDtoRequest {
    private Double quantity;

    private Date date;
}
