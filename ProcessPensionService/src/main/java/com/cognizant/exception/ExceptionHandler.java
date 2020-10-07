package com.cognizant.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseMessage> handleException(InvalidPensionDetailException e1) {
		ResponseMessage error = new ResponseMessage();
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setMessage(e1.getMessage());
		error.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseMessage> handleException(NullPointerException e2) {
		ResponseMessage error = new ResponseMessage();
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setMessage(e2.getMessage());
		error.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

}