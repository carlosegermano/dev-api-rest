package com.cursorestapi.apirest.services.exceptions;

public class InvalidUserException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public InvalidUserException(String msg) {
		super(msg);
	}
	
	public InvalidUserException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
