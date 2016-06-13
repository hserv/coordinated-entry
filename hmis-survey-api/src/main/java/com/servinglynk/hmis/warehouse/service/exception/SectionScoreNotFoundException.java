package com.servinglynk.hmis.warehouse.service.exception; 
 
@SuppressWarnings("serial")
public class SectionScoreNotFoundException extends RuntimeException { 
 
 /* 
 * Default exception message 
 */ 
   public static final String DEFAULT_MESSAGE = "SectionScore not found"; 

   public SectionScoreNotFoundException() {
       super(DEFAULT_MESSAGE);
   }
 
   public SectionScoreNotFoundException(String message) {
           super(message);
   }

   public SectionScoreNotFoundException(Throwable cause) {
           super(DEFAULT_MESSAGE, cause);
   }

   public SectionScoreNotFoundException(String message, Throwable cause) {
           super(message, cause);
   }

}
