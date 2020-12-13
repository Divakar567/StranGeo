package com.strangeo.user.repo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity(name = "user_authority_mapping")
public class UserAuthorityMapping {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "UserId is required")
	@Column(name = "user_id")
	private Long userId;

	@NotNull(message = "AuthorityId is required")
	@Column(name = "authority_id")
	private Long authorityId;
	
	@Column(name = "status")
	private Boolean status;
	
	@Column(name = "created_by")
	private Long createdBy;
	
	@Column(name = "updated_by")
	private Long updatedBy;
	
	@Column(name = "created_time")
	private Date createdTime;
	
	@Column(name = "updated_time")
	private Date updatedTime;
}
