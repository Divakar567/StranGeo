package com.strangeo.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AuthorizationRequest {
	@JsonProperty("response_type")
	private String responseType;
	@JsonProperty("client_id")
	private String clientId;
	@JsonProperty("redirect_uri")
	private String redirectURI;
	@JsonProperty("scope")
	private String scope;
	@JsonProperty("state")
	private String state;
	@JsonProperty("code_challenge")
	private String codeChallenge;
	@JsonProperty("code_challenge_method")
	private String codeChallengeMethod;
}
