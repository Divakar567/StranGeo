package com.strangeo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.strangeo.user.repo.UserProfileRepository;
import com.strangeo.user.repo.entity.UserProfile;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserProfileRepository userInfoRepo;

	@Override
	public UserProfile saveUserInfo(UserProfile userInfo) {
		return userInfoRepo.save(userInfo);
	}

	@Override
	public UserProfile getUserInfo(String userId) {
		return userInfoRepo.findById(userId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userId));
	}

}
