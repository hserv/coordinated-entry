package com.hserv.coordinatedentry.exception;


public class SurveyControllerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SurveyControllerException() {
		super();
	}

	public SurveyControllerException(String message) {
		super(message);
	}

	public SurveyControllerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SurveyControllerException(String message, Throwable cause) {
		super(message, cause);
	}

	public SurveyControllerException(Throwable cause) {
		super(cause);
	}

}
