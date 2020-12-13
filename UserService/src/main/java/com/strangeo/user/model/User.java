package com.creactor.user.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity(name = "user")
@Table(name = "user", schema="public")
public class User extends UserAuthDetails {

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@Nullable
	private String middleName;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@NotNull
	@JsonProperty(access = Access.READ_ONLY)
	@JsonFormat(shape = JsonFormat.Shape.NUMBER)
	@CreationTimestamp
	private Timestamp createdTime;

	@NotNull
	@JsonProperty(access = Access.READ_ONLY)
	@JsonFormat(shape = JsonFormat.Shape.NUMBER)
	@UpdateTimestamp
	private Timestamp updatedTime;

}
