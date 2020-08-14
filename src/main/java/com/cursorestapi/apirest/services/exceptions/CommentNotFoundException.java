package com.cursorestapi.apirest.services.exceptions;

public class CommentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CommentNotFoundException(String msg) {
		super(msg);
	}

	public CommentNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
