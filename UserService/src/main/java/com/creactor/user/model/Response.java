package com.creactor.user.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Response<T> implements Serializable {
	private static final long serialVersionUID = -778351422054250765L;
	
	private Status status;
	private T data;
	
	enum Status {
		SUCCESS, FAILED
	}
}
