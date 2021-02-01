package com.servinglynk.hmis.warehouse.service.exception; 
 
@SuppressWarnings("serial")
public class SurveyProjectNotFoundException extends RuntimeException { 
 
 /* 
 * Default exception message 
 */ 
   public static final String DEFAULT_MESSAGE = "SurveyProject not found"; 

   public SurveyProjectNotFoundException() {
       super(DEFAULT_MESSAGE);
   }
 
   public SurveyProjectNotFoundException(String message) {
           super(message);
   }

   public SurveyProjectNotFoundException(Throwable cause) {
           super(DEFAULT_MESSAGE, cause);
   }

   public SurveyProjectNotFoundException(String message, Throwable cause) {
           super(message, cause);
   }

}
