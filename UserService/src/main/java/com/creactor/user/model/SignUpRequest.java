package com.creactor.user.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import com.creactor.user.entity.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.ToString.Exclude;

@Data
public class SignUpRequest {
	@NotBlank
	private String userName;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@Nullable
	private String middleName;
	@Email
	private String email;
	@Exclude
	@NotBlank
	@Size(min = 4)
	private String password;
	@NotNull
	private Gender gender;
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.NUMBER)
	private Date dateOfBirth;
}
