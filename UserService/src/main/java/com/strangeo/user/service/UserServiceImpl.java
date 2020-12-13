package com.strangeo.user.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.strangeo.user.model.User;
import com.strangeo.user.repo.UserAuthorityMappingRepository;
import com.strangeo.user.repo.UserAuthorityRepository;
import com.strangeo.user.repo.UserCredentialsRepository;
import com.strangeo.user.repo.UserProfileRepository;
import com.strangeo.user.repo.entity.UserAuthority;
import com.strangeo.user.repo.entity.UserAuthorityMapping;
import com.strangeo.user.repo.entity.UserCredentials;
import com.strangeo.user.repo.entity.UserProfile;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserProfileRepository profileRepo;

	@Autowired
	private UserCredentialsRepository credentialsRepo;

	@Autowired
	private UserAuthorityRepository authorityRepo;

	@Autowired
	private UserAuthorityMappingRepository authorityMapppingRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User getUser(Long userId) {
		User user = new User();
		UserCredentials credentials = credentialsRepo.findById(userId)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found: " + userId));
		user.setUserId(credentials.getUserId());
		user.setEmail(credentials.getEmail());
		user.setPassword(credentials.getPassword());

		UserProfile userProfile = profileRepo.findByUserId(credentials.getUserId()).get();
		user.setFirstName(userProfile.getFirstName());
		user.setLastName(userProfile.getLastName());
		user.setMiddleName(userProfile.getMiddleName());
		user.setGender(userProfile.getGender());
		user.setDateOfBirth(userProfile.getDateOfBirth());
		user.setCreatedTime(userProfile.getCreatedTime());
		user.setUpdatedTime(userProfile.getUpdatedTime());

		Set<UserAuthorityMapping> mappings = authorityMapppingRepo.findByUserId(credentials.getUserId());
		if (!mappings.isEmpty()) {
			List<UserAuthority> authorities = authorityRepo.findAllById(
					mappings.stream().map(mapping -> mapping.getAuthorityId()).collect(Collectors.toSet()));
			user.setAuthorities(authorities);
		}
		return user;
	}

	@Transactional
	@Override
	public User saveUser(User user) {
		UserCredentials credentials = saveUserCredentials(user);
		user.setUserId(credentials.getUserId());
		user.setCreatedTime(credentials.getCreatedTime());
		user.setUpdatedTime(credentials.getUpdatedTime());
		user.setPassword(null);
		log.info("UserCredentials: {}", credentials);

		saveUserProfile(user);
		saveUserAuthorityMappings(user);
		return user;
	}

	private List<UserAuthorityMapping> saveUserAuthorityMappings(User user) {
		if (user.getAuthorities() == null) {
			Collections.emptySet();
		}

		Set<UserAuthorityMapping> mappings = user.getAuthorities().stream().map(authority -> {
			UserAuthorityMapping mapping = new UserAuthorityMapping();
			mapping.setUserId(user.getUserId());
			mapping.setAuthorityId(authority.getId());
			mapping.setStatus(true);
			mapping.setCreatedBy(1L);
			mapping.setCreatedTime(user.getCreatedTime());
			mapping.setUpdatedBy(1L);
			mapping.setUpdatedTime(user.getUpdatedTime());
			return mapping;
		}).collect(Collectors.toSet());

		return authorityMapppingRepo.saveAll(mappings);
	}

	private UserCredentials saveUserCredentials(User user) {
		UserCredentials credentials = new UserCredentials();
		credentials.setUsername(user.getUsername());
		credentials.setEmail(user.getEmail());
		credentials.setPassword(passwordEncoder.encode(user.getPassword()));
		credentials.setCreatedTime(new Date());
		credentials.setUpdatedTime(credentials.getCreatedTime());
		return credentialsRepo.save(credentials);
	}

	private UserProfile saveUserProfile(User user) {
		UserProfile profile = new UserProfile();
		profile.setUserId(user.getUserId());
		profile.setEmail(user.getEmail());
		profile.setFirstName(user.getFirstName());
		profile.setLastName(user.getLastName());
		profile.setMiddleName(user.getMiddleName());
		profile.setGender(user.getGender());
		profile.setDateOfBirth(user.getDateOfBirth());
		profile.setCreatedTime(user.getCreatedTime());
		profile.setUpdatedTime(user.getUpdatedTime());
		profile = profileRepo.save(profile);
		return profile;
	}

}
