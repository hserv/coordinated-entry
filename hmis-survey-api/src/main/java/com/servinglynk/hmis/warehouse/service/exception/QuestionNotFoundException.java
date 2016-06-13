package com.servinglynk.hmis.warehouse.service.exception; 
 
@SuppressWarnings("serial")
public class QuestionNotFoundException extends RuntimeException { 
 
 /* 
 * Default exception message 
 */ 
   public static final String DEFAULT_MESSAGE = "Question not found"; 

   public QuestionNotFoundException() {
       super(DEFAULT_MESSAGE);
   }
 
   public QuestionNotFoundException(String message) {
           super(message);
   }

   public QuestionNotFoundException(Throwable cause) {
           super(DEFAULT_MESSAGE, cause);
   }

   public QuestionNotFoundException(String message, Throwable cause) {
           super(message, cause);
   }

}
