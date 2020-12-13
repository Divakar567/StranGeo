package com.strangeo.user.service;

import com.strangeo.user.model.User;

public interface UserService {

	public User getUser(Long userId);

	public User saveUser(User user);

}
