package com.strangeo.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TokenRequest {
	@JsonProperty("grant_type")
	private GrantType grantType;
	@JsonProperty("code")
	private String code;
	@JsonProperty("redirect_uri")
	private String redirectURI;
	@JsonProperty("code_verifier")
	private String codeVerifier;
	
	public enum GrantType{
		authorization_code
	}
}
