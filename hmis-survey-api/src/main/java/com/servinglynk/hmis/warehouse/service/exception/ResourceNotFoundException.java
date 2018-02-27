package com.servinglynk.hmis.warehouse.service.exception;

public class ResourceNotFoundException extends RuntimeException {

	   public static final String DEFAULT_MESSAGE = "Resource not found"; 

	   public ResourceNotFoundException() {
	       super(DEFAULT_MESSAGE);
	   }
	 
	   public ResourceNotFoundException(String message) {
	           super(message);
	   }

	   public ResourceNotFoundException(Throwable cause) {
	           super(DEFAULT_MESSAGE, cause);
	   }

	   public ResourceNotFoundException(String message, Throwable cause) {
	           super(message, cause);
	   }

}