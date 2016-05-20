package com.hserv.coordinatedentry.housingmatching.model;

import java.io.Serializable;

import com.servinglynk.hmis.warehouse.client.model.ApiMethodAuthorizationCheck;

public class AuthResponseWrapper implements Serializable{
	private ApiMethodAuthorizationCheck apiMethodAuthorizationCheck;
	private AuthError errors;

	public ApiMethodAuthorizationCheck getApiMethodAuthorizationCheck() {
		return apiMethodAuthorizationCheck;
	}

	public void setApiMethodAuthorizationCheck(ApiMethodAuthorizationCheck apiMethodAuthorizationCheck) {
		this.apiMethodAuthorizationCheck = apiMethodAuthorizationCheck;
	}

	public AuthError getErrors() {
		return errors;
	}

	public void setErrors(AuthError errors) {
		this.errors = errors;
	}

}
