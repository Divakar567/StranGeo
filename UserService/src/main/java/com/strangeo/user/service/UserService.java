package com.strangeo.user.service;

import com.strangeo.user.repo.entity.UserProfile;

public interface UserService {

	public UserProfile saveUserInfo(UserProfile userInfo);

	public UserProfile getUserInfo(String userId);

}
