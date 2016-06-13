package com.servinglynk.hmis.warehouse.service.exception; 
 
@SuppressWarnings("serial")
public class SurveyNotFoundException extends RuntimeException { 
 
 /* 
 * Default exception message 
 */ 
   public static final String DEFAULT_MESSAGE = "survey not found"; 

   public SurveyNotFoundException() {
       super(DEFAULT_MESSAGE);
   }
 
   public SurveyNotFoundException(String message) {
           super(message);
   }

   public SurveyNotFoundException(Throwable cause) {
           super(DEFAULT_MESSAGE, cause);
   }

   public SurveyNotFoundException(String message, Throwable cause) {
           super(message, cause);
   }

}
