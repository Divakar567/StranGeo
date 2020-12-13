package com.strangeo.user.repo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
@Entity(name = "user_authority")
public class UserAuthority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String role;

	private String permission;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TYPE type;

	@Type(type = "text")
	private String description;

	@Column(name = "authority")
	private String authority;

	@Override
	public String getAuthority() {
		String authority = this.type + "_" + this.role.toUpperCase();
		if (this.permission != null) {
			authority += "_" + this.permission.toUpperCase();
		}
		return authority;
	}

	public enum TYPE {
		ROLE, PERMISSION
	}
}
