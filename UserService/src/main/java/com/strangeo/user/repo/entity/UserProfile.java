package com.strangeo.user.repo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

import lombok.Data;

@Data
@Entity(name = "user_profile")
public class UserProfile {

	@Id
	@Column(name = "user_id")
	@NotNull(message = "UserId is required")
	private Long userId;
	
	@NotEmpty(message = "Email is required")
	@Email(message = "Invalid Email")
	@NaturalId
	@Column(name = "email")
	private String email;
	
	@NotEmpty(message = "Firstname is required")
	@Column(name = "first_name")
	private String firstName;
	
	@NotEmpty(message = "Lastname is required")
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "middle_name")
	private String middleName;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private Gender gender;
	
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	
	@Column(name = "created_time")
	private Date createdTime;
	
	@Column(name = "updated_time")
	private Date updatedTime;
	
	public enum Gender {
		MALE, FEMALE
	}
}
