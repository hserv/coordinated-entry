package com.servinglynk.hmis.warehouse.service.exception; 
 
@SuppressWarnings("serial")
public class PickListValueNotFoundException extends RuntimeException { 
 
 /* 
 * Default exception message 
 */ 
   public static final String DEFAULT_MESSAGE = "PickListValue not found"; 

   public PickListValueNotFoundException() {
       super(DEFAULT_MESSAGE);
   }
 
   public PickListValueNotFoundException(String message) {
           super(message);
   }

   public PickListValueNotFoundException(Throwable cause) {
           super(DEFAULT_MESSAGE, cause);
   }

   public PickListValueNotFoundException(String message, Throwable cause) {
           super(message, cause);
   }

}
