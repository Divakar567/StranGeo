package com.creactor.user.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.ToString.Exclude;

@Data
@MappedSuperclass
public class UserAuthDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotBlank
	private String userName;

	@Email
	@NaturalId
	private String email;

	@Exclude
	@NotBlank
	@Size(min = 4)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	// @formatter:off
	@ManyToMany
	@JoinTable(name = "user_authority", 
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id")
	)
	private Set<Authority> authorities = new HashSet<Authority>();
	// @formatter:on

	public String getPassword() {
		return this.password;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public String getUsername() {
		return this.userName;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public boolean isEnabled() {
		return true;
	}

}
