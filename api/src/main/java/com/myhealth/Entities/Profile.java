package com.myhealth.Entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.myhealth.Dto.Requests.ProfileDtoRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "profile")
@NoArgsConstructor
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
	private User user;

	@Column(name = "name", length = 32, nullable = false)
	private String name;

	@Column(name = "last_name", length = 32, nullable = false)
	private String lastName;

	@Column(name = "gender", length = 32, nullable = false)
	private String gender;

	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date", nullable = false)
	Date birthDate;

	@Column(name = "image_url", length = 65536)
	private String imageUrl;

	@OneToOne(mappedBy = "profile")
	private Specialist specialist;

	@OneToOne(mappedBy = "profile")
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	public Profile(Role role, User user, ProfileDtoRequest profileDtoRequest) {
		this.role = role;
		this.user = user;
		this.name = profileDtoRequest.getName();
		this.lastName = profileDtoRequest.getLastName();
		this.gender = profileDtoRequest.getGender();
		this.birthDate = profileDtoRequest.getBirthDate();
		this.imageUrl = profileDtoRequest.getImageUrl();
	}
}
