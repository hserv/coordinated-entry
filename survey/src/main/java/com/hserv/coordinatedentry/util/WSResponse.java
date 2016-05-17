package com.hserv.coordinatedentry.util;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class WSResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	public WSResponse() {

	}

	private String statusCode = ApplicationConstants._200;

	private String status;

	private String statusMessage = ApplicationConstants.SUCCESS;

	private String errorCode;

	private String erroMessage;

	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErroMessage() {
		return erroMessage;
	}

	public void setErroMessage(String erroMessage) {
		this.erroMessage = erroMessage;
	}

}
