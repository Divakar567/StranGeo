package com.strangeo.user.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.strangeo.user.repo.entity.UserAuthorityMapping;

public interface UserAuthorityMappingRepository extends JpaRepository<UserAuthorityMapping, Long> {

	public Set<UserAuthorityMapping> findByUserId(Long userId);

}
