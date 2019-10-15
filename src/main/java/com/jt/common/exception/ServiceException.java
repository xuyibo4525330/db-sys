package com.jt.common.exception;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = -2850276659806418376L;
	public ServiceException() {
		super();
	}
	public ServiceException(String message) {
		super(message);
	}
	public ServiceException(Throwable cause) {
		super(cause);
	}
}
