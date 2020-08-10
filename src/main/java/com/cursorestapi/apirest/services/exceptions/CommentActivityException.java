package com.cursorestapi.apirest.services.exceptions;

public class CommentActivityException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public CommentActivityException(String msg) {
		super(msg);
	}
	
	public CommentActivityException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
