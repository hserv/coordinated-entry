package com.servinglynk.hmis.warehouse.service.exception; 
 
@SuppressWarnings("serial")
public class SurveyCategoryNotFoundException extends RuntimeException { 
 
 /* 
 * Default exception message 
 */ 
   public static final String DEFAULT_MESSAGE = "SurveyCategory not found"; 

   public SurveyCategoryNotFoundException() {
       super(DEFAULT_MESSAGE);
   }
 
   public SurveyCategoryNotFoundException(String message) {
           super(message);
   }

   public SurveyCategoryNotFoundException(Throwable cause) {
           super(DEFAULT_MESSAGE, cause);
   }

   public SurveyCategoryNotFoundException(String message, Throwable cause) {
           super(message, cause);
   }

}
