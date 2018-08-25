package com.rituj.userService.UserService.exception;

public class InvalidDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidDataException() {
		super();
	}

	public InvalidDataException(String message) {
		super(message);
	}

	public InvalidDataException(String message, Throwable cause) {
		super(message, cause);
	}

}
