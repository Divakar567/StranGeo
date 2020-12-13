package com.strangeo.user.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserAuthDetails {
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	private String accessToken;
	private String rememberMeToken;
	private Boolean rememberMe = false;
}
