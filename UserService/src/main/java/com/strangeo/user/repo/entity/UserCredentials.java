package com.strangeo.user.repo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.NaturalId;

import lombok.Data;
import lombok.ToString.Exclude;

@Data
@Entity(name = "user_credentials")
public class UserCredentials {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "username")
	@NotEmpty(message = "UserName is required")
	private String username;

	@NaturalId
	@Column(name = "email", nullable = false)
	@NotEmpty(message = "Email is required")
	@Email(message = "Invalid email")
	private String email;

	@Exclude
	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "is_activated", nullable = true)
	private boolean activated;

	@Column(name = "created_time", nullable = true)
	private Date createdTime;

	@Column(name = "updated_time", nullable = true)
	private Date updatedTime;
}
