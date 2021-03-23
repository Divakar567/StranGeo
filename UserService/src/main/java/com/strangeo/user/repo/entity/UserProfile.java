package com.strangeo.user.repo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
@Entity(name = "user_profile")
public class UserProfile {
	@Id
	@Column(name = "id", nullable = false, length = 32, updatable = false)
	private String userId;
	
	@Column(name = "user_name", nullable = false, length = 64, unique = true)
	private String userName;
	@NaturalId(mutable = true)
	@Column(name = "email", nullable = false, length = 64, unique = true)
	private String email;
	
	@Column(name = "first_name", nullable = false, length = 64)
	private String firstName;
	@Column(name = "last_name", nullable = false, length = 64)
	private String lastName;
	
	@Column(name = "date_of_birth", nullable = false)
	private Date dateOfBirth;
	@Enumerated(EnumType.STRING)
	@Column(name = "gender", nullable = false)
	private Gender gender;
	
	@CreatedBy
	@Column(name = "created_by", nullable = false, length = 32, updatable = false)
	private String createdBy;
	@LastModifiedBy
	@Column(name = "last_modified_by", nullable = false, length = 32)
	private String lastModifiedBy;
	@CreatedDate
	@Column(name = "created_date", nullable = false, updatable = false)
	private Date createdDate;
	@LastModifiedDate
	@Column(name = "last_modified_date", nullable = false)
	private Date lastModifiedDate;
	
	public enum Gender {
		MALE, FEMALE
	}
}
