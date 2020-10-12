package com.creactor.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.creactor.user.entity.User;
import com.creactor.user.model.SignUpRequest;

/**
 * 
 * @author divakar.budumuru
 * @since 10-10-2020
 *
 */

public interface UserService extends UserDetailsService {

	public User registerUser(SignUpRequest signUpRequest);
	
}
