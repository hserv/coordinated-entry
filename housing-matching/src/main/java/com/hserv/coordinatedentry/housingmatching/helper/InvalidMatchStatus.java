package com.hserv.coordinatedentry.housingmatching.helper;

public class InvalidMatchStatus extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static final String DEFAULT_MESSAGE = "Invalid status";

	public InvalidMatchStatus() {
		super(DEFAULT_MESSAGE);
	}

	public InvalidMatchStatus(String message) {
		super(message);
	}

	public InvalidMatchStatus(Throwable cause) {
		super(DEFAULT_MESSAGE, cause);
	}

	public InvalidMatchStatus(String message, Throwable cause) {
		super(message, cause);
	}
}
