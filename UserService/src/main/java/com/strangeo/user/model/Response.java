package com.strangeo.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
	private StatusMessage status;
	private T content;
	
	public enum StatusMessage {
		SUCCESS, FAILED
	}

	public static <T> Response<T> ofSuccess(T data) {
		return new Response<T>(StatusMessage.SUCCESS, data);
	}
	
	public static <T> Response<T> ofFailure(T data) {
		return new Response<T>(StatusMessage.FAILED, data);
	}
}
