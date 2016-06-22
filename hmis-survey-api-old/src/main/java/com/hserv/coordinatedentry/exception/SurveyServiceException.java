package com.hserv.coordinatedentry.exception;


public class SurveyServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SurveyServiceException() {
		super();
	}

	public SurveyServiceException(String message) {
		super(message);
	}

	public SurveyServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SurveyServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public SurveyServiceException(Throwable cause) {
		super(cause);
	}

}
