package com.strangeo.user.model;

import java.util.Date;

import com.strangeo.user.repo.entity.UserProfile.Gender;

import lombok.Data;

@Data
public class SignUpRequest {
	private String userName;
	private String firstName;
	private String lastName;
	private String middleName;
	private String email;
	private Date dateOfBirth;
	private Gender gender;
	private String password;
	
	private SignUpType signUpType;
	public enum SignUpType {
		WEB
	}
}
