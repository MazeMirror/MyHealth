package com.myhealth.Dto.Responses;

import java.util.Date;

import lombok.Data;

@Data
public class ProfileDtoResponse {

	private Long id;

	private Long userId;

	private String name;

	private String lastName;

	private String gender;

	private Date birthDate;

	private String imageUrl;

	private Long roleId;
}
