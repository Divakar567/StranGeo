package com.strangeo.user.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.strangeo.user.repo.entity.UserAuthority;
import com.strangeo.user.repo.entity.UserProfile.Gender;

import lombok.Data;
import lombok.ToString.Exclude;

@Data
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long userId;
	@NotEmpty
	@JsonProperty("userName")
	private String username;
	@Email
	private String email;
	@NotEmpty
	private String firstName;
	private String lastName;
	private String middleName;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@DateTimeFormat(iso = ISO.DATE)
	private Date dateOfBirth;
	@Exclude
	private String password;
	private Collection<UserAuthority> authorities;
	@JsonFormat(shape = JsonFormat.Shape.NUMBER)
	private Date createdTime;
	@JsonFormat(shape = JsonFormat.Shape.NUMBER)
	private Date updatedTime;

	@Override
	public String getUsername() {
		return StringUtils.isEmpty(this.username) ? this.email : this.username;
	}

	@JsonSetter(value = "password")
	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	public String getPassword() {
		return this.password;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}
}