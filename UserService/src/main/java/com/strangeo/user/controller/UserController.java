package com.strangeo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.strangeo.user.model.Response;
import com.strangeo.user.repo.entity.UserProfile;
import com.strangeo.user.service.UserService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/users")
	@PreAuthorize("hasRole('ROLE_USER')")
	@CachePut(cacheNames = {"users"}, key = "#userInfo.id")
	public ResponseEntity<?> saveUserInfo(@RequestBody UserProfile userInfo) {
		try {
			userInfo = userService.saveUserInfo(userInfo);
			return ResponseEntity.accepted().body(Response.ofSuccess(userInfo));
		} catch(ResponseStatusException e) {
			log.error("Handled error while saving user info: ", e);
			throw e;
		} catch(Exception e) {
			log.error("Unhandled error while saving user info: ", e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!");
		}
	}

	@GetMapping("/users/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	@Cacheable(cacheNames = {"users"}, key = "#userId")
	public ResponseEntity<?> getUser(@PathVariable(value = "userId") String userId) {
		try {
			UserProfile userInfo = userService.getUserInfo(userId);
			return ResponseEntity.ok().body(Response.ofSuccess(userInfo));
		} catch(ResponseStatusException e) {
			log.error("Handled error while getting user info: ", e);
			throw e;
		} catch(Exception e) {
			log.error("Unhandled error while getting user info: ", e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!");
		}
	}
}
