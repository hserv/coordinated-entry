package com.hserv.coordinatedentry.housingmatching.helper;

public class ProcessAlreadyRunningException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static final String DEFAULT_MESSAGE = "Score processing currently in progress.";

	public ProcessAlreadyRunningException() {
		super(DEFAULT_MESSAGE);
	}

	public ProcessAlreadyRunningException(String message) {
		super(message);
	}

	public ProcessAlreadyRunningException(Throwable cause) {
		super(DEFAULT_MESSAGE, cause);
	}

	public ProcessAlreadyRunningException(String message, Throwable cause) {
		super(message, cause);
	}
}