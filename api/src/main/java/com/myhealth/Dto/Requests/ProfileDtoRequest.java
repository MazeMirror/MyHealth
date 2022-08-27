package com.myhealth.Dto.Requests;

import java.util.Date;

import lombok.Data;

@Data
public class ProfileDtoRequest {

	private Long userId;

	private String name;

	private String lastName;

	private String gender;

	Date birthDate;

	private String imageUrl;

	private Long roleId;
}
