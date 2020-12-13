package com.strangeo.user.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.strangeo.user.repo.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

	Optional<UserProfile> findByUserId(Long userId);

	Optional<UserProfile> findByEmail(String email);
}
