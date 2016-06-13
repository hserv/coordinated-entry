package com.servinglynk.hmis.warehouse.service.exception; 
 
@SuppressWarnings("serial")
public class ResponseNotFoundException extends RuntimeException { 
 
 /* 
 * Default exception message 
 */ 
   public static final String DEFAULT_MESSAGE = "Response not found"; 

   public ResponseNotFoundException() {
       super(DEFAULT_MESSAGE);
   }
 
   public ResponseNotFoundException(String message) {
           super(message);
   }

   public ResponseNotFoundException(Throwable cause) {
           super(DEFAULT_MESSAGE, cause);
   }

   public ResponseNotFoundException(String message, Throwable cause) {
           super(message, cause);
   }

}
