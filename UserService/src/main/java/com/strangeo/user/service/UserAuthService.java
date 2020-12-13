package com.strangeo.user.service;

import com.strangeo.user.model.SignUpRequest;
import com.strangeo.user.model.User;
import com.strangeo.user.model.UserAuthDetails;

public interface UserAuthService {

	public User authenticateUser(UserAuthDetails signInRequest);

	public User signUpUser(SignUpRequest signUpRequest);
}
