package com.hserv.coordinatedentry.housingmatching.model;

import java.util.List;

public class AccountModel {

	String accountId;
	String emailAddress;
	String firstName;
	String middleName;
	String lastName;
	boolean twoFactorAuthentication;
	List<PreferenceModel> preferences;
	List<RoleModel> roles;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isTwoFactorAuthentication() {
		return twoFactorAuthentication;
	}

	public void setTwoFactorAuthentication(boolean twoFactorAuthentication) {
		this.twoFactorAuthentication = twoFactorAuthentication;
	}

	public List<PreferenceModel> getPreferences() {
		return preferences;
	}

	public void setPreferences(List<PreferenceModel> preferences) {
		this.preferences = preferences;
	}

	public List<RoleModel> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleModel> roles) {
		this.roles = roles;
	}

}
