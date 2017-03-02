package com.hserv.coordinatedentry.housingmatching.model;

public class MatchStatusRemarks {

	private Long statusCode;
	private String reason;
	
	public Long getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Long statusCode) {
		this.statusCode = statusCode;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}