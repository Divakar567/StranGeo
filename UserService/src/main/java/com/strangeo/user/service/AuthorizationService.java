package com.strangeo.user.service;

import com.strangeo.user.model.AuthorizationRequest;
import com.strangeo.user.model.AuthorizationResponse;
import com.strangeo.user.model.TokenRequest;
import com.strangeo.user.model.TokenResponse;

public interface AuthorizationService {
	
	public AuthorizationResponse getAuthorization(AuthorizationRequest authorizationRequest);
	
	public TokenResponse getAccessToken(TokenRequest tokenRequest);
	
}
