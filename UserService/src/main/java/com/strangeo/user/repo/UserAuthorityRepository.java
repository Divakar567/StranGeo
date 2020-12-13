package com.strangeo.user.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.strangeo.user.repo.entity.UserAuthority;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {
	public Set<UserAuthority> findByRole(String role);
}
