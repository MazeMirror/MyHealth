package com.myhealth.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.myhealth.Dto.Requests.UserDtoRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	// @OneToOne(mappedBy = "user")
	// private Profile profile;

	public User(UserDtoRequest userDtoRequest) {
		this.email = userDtoRequest.getEmail();
		this.password = userDtoRequest.getPassword();
	}
}
