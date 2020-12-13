package com.creactor.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.creactor.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	UserDetails findByUserName(String username);

}
