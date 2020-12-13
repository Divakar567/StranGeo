package com.creactor.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creactor.user.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	public Authority findByKey(String key);
}
