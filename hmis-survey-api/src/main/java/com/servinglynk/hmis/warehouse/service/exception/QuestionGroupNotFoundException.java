package com.servinglynk.hmis.warehouse.service.exception; 
 
@SuppressWarnings("serial")
public class QuestionGroupNotFoundException extends RuntimeException { 
 
 /* 
 * Default exception message 
 */ 
   public static final String DEFAULT_MESSAGE = "QuestionGroup not found"; 

   public QuestionGroupNotFoundException() {
       super(DEFAULT_MESSAGE);
   }
 
   public QuestionGroupNotFoundException(String message) {
           super(message);
   }

   public QuestionGroupNotFoundException(Throwable cause) {
           super(DEFAULT_MESSAGE, cause);
   }

   public QuestionGroupNotFoundException(String message, Throwable cause) {
           super(message, cause);
   }

}
