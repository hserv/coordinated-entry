package com.servinglynk.hmis.warehouse.service.exception; 
 
@SuppressWarnings("serial")
public class SurveySectionNotFoundException extends RuntimeException { 
 
 /* 
 * Default exception message 
 */ 
   public static final String DEFAULT_MESSAGE = "SurveySection not found"; 

   public SurveySectionNotFoundException() {
       super(DEFAULT_MESSAGE);
   }
 
   public SurveySectionNotFoundException(String message) {
           super(message);
   }

   public SurveySectionNotFoundException(Throwable cause) {
           super(DEFAULT_MESSAGE, cause);
   }

   public SurveySectionNotFoundException(String message, Throwable cause) {
           super(message, cause);
   }

}
