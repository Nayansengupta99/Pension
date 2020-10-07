package com.cognizant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidPensionDetailException extends Exception {

	public InvalidPensionDetailException() {
		super("Invalid Pension Detail");
	}

}
