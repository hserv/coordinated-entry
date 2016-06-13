package com.servinglynk.hmis.warehouse.service.exception; 
 
@SuppressWarnings("serial")
public class PickListGroupNotFoundException extends RuntimeException { 
 
 /* 
 * Default exception message 
 */ 
   public static final String DEFAULT_MESSAGE = "PickListGroup not found"; 

   public PickListGroupNotFoundException() {
       super(DEFAULT_MESSAGE);
   }
 
   public PickListGroupNotFoundException(String message) {
           super(message);
   }

   public PickListGroupNotFoundException(Throwable cause) {
           super(DEFAULT_MESSAGE, cause);
   }

   public PickListGroupNotFoundException(String message, Throwable cause) {
           super(message, cause);
   }

}
