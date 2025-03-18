package com.wissen.ems.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	@ExceptionHandler
	public ResponseEntity<String> handleBusinessRuleViolationException(BusinessRuleViolationException ex) {
		// The 422 (Unprocessable Entity) status code means the server understands that
		// the request is valid. However, it cannot process the request because the
		// content within the request makes it unprocessable due to business
		// constraints.
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
	}
}
