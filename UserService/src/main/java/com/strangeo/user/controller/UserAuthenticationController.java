package com.strangeo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strangeo.user.model.SignUpRequest;
import com.strangeo.user.model.User;
import com.strangeo.user.model.UserAuthDetails;
import com.strangeo.user.service.UserAuthService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/auth")
public class UserAuthenticationController {

	@Autowired
	private UserAuthService userAuthService;

	@PreAuthorize(value = "permitAll()")
	@PostMapping("/signup")
	public ResponseEntity<User> signUpUser(@RequestBody SignUpRequest signUpRequest) {
		log.info("SignUpReuqest recieved: {}", signUpRequest);
		return new ResponseEntity<User>(userAuthService.signUpUser(signUpRequest), HttpStatus.CREATED);
	}

	@PreAuthorize(value = "permitAll()")
	@PostMapping(path = "/signin", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> signInUser(@RequestBody UserAuthDetails signInRequest) {
		log.info("SignInReuqest recieved: {}", signInRequest);
		return new ResponseEntity<User>(userAuthService.authenticateUser(signInRequest), HttpStatus.OK);
	}
	
	@PreAuthorize(value = "permitAll()")
	@PostMapping(path = "/token", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> provideToken(@RequestBody UserAuthDetails signInRequest) {
		log.info("SignInReuqest recieved: {}", signInRequest);
		return new ResponseEntity<User>(userAuthService.authenticateUser(signInRequest), HttpStatus.OK);
	}
}
