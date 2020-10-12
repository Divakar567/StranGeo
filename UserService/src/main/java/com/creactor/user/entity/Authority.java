package com.creactor.user.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
@Entity(name = "authority")
@Table(name = "authority", schema="public")
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NaturalId
	private String key;
	
	private String name;

	@Enumerated(EnumType.STRING)
	private TYPE type;
	
	@Type(type = "text")
	private String description;
	
	public enum TYPE {
		ROLE, PERMISSION
	}

	@Override
	public String getAuthority() {
		return this.key;
	}
}
