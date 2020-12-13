package com.creactor.user.config;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class UserServiceControllerAdvisor extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({ValidationException.class, ConstraintViolationException.class})
	public ResponseEntity<?> handleUserNotFoundException(ValidationException e, WebRequest request) {
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalida Request Data", e);
	}
}
