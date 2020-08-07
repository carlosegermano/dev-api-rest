package com.cursorestapi.apirest.services.exceptions;

public class DuplicatedUserException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DuplicatedUserException(String msg) {
		super(msg);
	}
	
	public DuplicatedUserException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
