package com.strangeo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.strangeo.user.model.SignUpRequest;
import com.strangeo.user.model.User;
import com.strangeo.user.model.UserAuthDetails;
import com.strangeo.user.repo.UserAuthorityRepository;
import com.strangeo.user.repo.UserCredentialsRepository;
import com.strangeo.user.repo.entity.UserCredentials;

@Service
public class UserAuthServiceImpl implements UserAuthService, UserDetailsService {

	@Autowired
	private UserService userService;

	@Autowired
	private UserCredentialsRepository credentialsRepo;

	@Autowired
	private UserAuthorityRepository authorityRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		UserCredentials credentials = credentialsRepo.findByUsername(username)
				.orElseGet(() -> credentialsRepo.findByEmail(username)
						.orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username)));
		return userService.getUser(credentials.getUserId());
	}

	@Override
	public User authenticateUser(UserAuthDetails authDetails) {
		User user = loadUserByUsername(authDetails.getUsername());
		if (!passwordEncoder.matches(authDetails.getPassword(), user.getPassword())) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
		}
		return user;
	}
	
	@Override
	public User signUpUser(SignUpRequest signUpRequest) {
		User user = new User();
		user.setUsername(signUpRequest.getUserName());
		user.setEmail(signUpRequest.getEmail());
		user.setFirstName(signUpRequest.getFirstName());
		user.setLastName(signUpRequest.getLastName());
		user.setMiddleName(signUpRequest.getMiddleName());
		user.setDateOfBirth(signUpRequest.getDateOfBirth());
		user.setGender(signUpRequest.getGender());
		user.setPassword(signUpRequest.getPassword());
		user.setAuthorities(authorityRepo.findByRole("USER"));
		return userService.saveUser(user);
	}
}
