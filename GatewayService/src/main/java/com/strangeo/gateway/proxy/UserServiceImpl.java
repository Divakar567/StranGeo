package com.creactor.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.creactor.user.entity.User;
import com.creactor.user.model.SignUpRequest;
import com.creactor.user.repo.AuthorityRepository;
import com.creactor.user.repo.UserRepository;

import lombok.extern.log4j.Log4j2;

/**
 * 
 * @author divakar.budumuru
 * @since 11-10-2020
 *
 */
@Log4j2
@Primary
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AuthorityRepository authRepo;

	private List<String> DEFAULT_ROLES = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
		{
			add("USER");
			add("CITIZEN");
		}
	};

	@Override
	public User registerUser(SignUpRequest signUpRequest) {

		User user = new User();
		user.setUserName(signUpRequest.getUserName());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(this.passwordEncoder.encode(signUpRequest.getPassword()));

		user.setFirstName(signUpRequest.getFirstName());
		user.setLastName(signUpRequest.getLastName());
		user.setMiddleName(signUpRequest.getMiddleName());
		user.setGender(signUpRequest.getGender());
		user.setDateOfBirth(signUpRequest.getDateOfBirth());
		
		DEFAULT_ROLES.forEach(key -> user.getAuthorities().add(authRepo.findByKey(key)));
		
		return userRepo.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("User login request: {}", username);
		return userRepo.findByUserName(username);
	}

}
