package com.servinglynk.hmis.warehouse.service.exception; 
 
@SuppressWarnings("serial")
public class SectionQuestionMappingNotFoundException extends RuntimeException { 
 
 /* 
 * Default exception message 
 */ 
   public static final String DEFAULT_MESSAGE = "SectionQuestionMapping not found"; 

   public SectionQuestionMappingNotFoundException() {
       super(DEFAULT_MESSAGE);
   }
 
   public SectionQuestionMappingNotFoundException(String message) {
           super(message);
   }

   public SectionQuestionMappingNotFoundException(Throwable cause) {
           super(DEFAULT_MESSAGE, cause);
   }

   public SectionQuestionMappingNotFoundException(String message, Throwable cause) {
           super(message, cause);
   }

}
