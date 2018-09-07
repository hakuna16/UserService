package com.rituj.userService.UserService.exception;

public class UserNotEnableException extends RuntimeException {

	private static final long serialVersionUID = 1209097119166820779L;

	public UserNotEnableException() {
		super();
	}

	public UserNotEnableException(final String message) {
		super(message);
	}

	public UserNotEnableException(final String message, final Throwable throwable) {
		super(message, throwable);
	}

}
