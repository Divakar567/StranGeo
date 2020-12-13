package com.strangeo.user.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.strangeo.user.repo.entity.UserCredentials;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long> {

	public Optional<UserCredentials> findByEmail(String username);

	public Optional<UserCredentials> findByUsername(String username);

}
