package com.strangeo.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AuthorizationResponse {
	private String redirectURI;
	private String authorizationCode;
	private String state;
	
	@JsonProperty("id_token")
	private String idToken;
	@JsonProperty("access_token")
	private String accessToken;
}
