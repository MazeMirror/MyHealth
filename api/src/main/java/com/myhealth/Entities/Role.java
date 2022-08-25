package com.myhealth.Entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "role")
public class Role {
	@Id
	private Long id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "role")
	private Set<Profile> profiles;

}
