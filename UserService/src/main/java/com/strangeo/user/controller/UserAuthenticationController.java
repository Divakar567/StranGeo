package com.creactor.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.creactor.user.entity.User;
import com.creactor.user.model.SignUpRequest;
import com.creactor.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author divakar.budumuru
 * @since 10-10-2020
 *
 */

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(path = "/public/signup", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> signUpUser(@RequestBody SignUpRequest signUpRequest) {
		try {
			User user = userService.registerUser(signUpRequest);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (ResponseStatusException e) {
			throw e;
		} catch (Exception e) {
			log.error("Exception while user signup: ", e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User signup failed");
		}
	}
}
