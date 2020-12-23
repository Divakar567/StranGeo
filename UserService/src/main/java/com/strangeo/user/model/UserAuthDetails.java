package com.strangeo.user.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;

@Data
public class UserAuthDetails {
	@NotBlank
	@JsonAlias({"email"})
	private String username;
	@NotBlank
	private String password;
	private String accessToken;
	private String rememberMeToken;
	private Boolean rememberMe = false;
}
