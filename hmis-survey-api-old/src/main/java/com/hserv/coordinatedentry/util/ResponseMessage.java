package com.hserv.coordinatedentry.util;

public enum ResponseMessage {

	SUCCESS("success"),FAILURE("failure");
	
	private String name;
	
	ResponseMessage(String name){
		this.name = name;
	}
}
