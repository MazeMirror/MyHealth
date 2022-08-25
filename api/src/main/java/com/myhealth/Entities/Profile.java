package com.myhealth.Entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "profile")
public class Profile {
	@Id
	@Column(name = "id")
	private Long id;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private Usuario user;

	@Column(name = "name", length = 32)
	private String name;

	@Column(name = "last_name", length = 32)
	private String lastName;

	@Column(name = "gender", length = 16)
	private String gender;

	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date")
	Date birthDate;

	@Column(name = "image_url", length = 65536)
	private String imageUrl;

	@OneToOne(mappedBy = "profile")
	private Specialist specialist;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;
}
