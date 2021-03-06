package com.hserv.coordinatedentry.housingmatching.model;

import java.util.Date;
import java.util.UUID;

public class ClientModel {

	private UUID id;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date dob;
	private String phoneNumber;
	private String emailAddress;
	private UUID clientDedupId;
	private String schemaYear;
	
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
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getClientDedupId() {
		return clientDedupId;
	}
	public void setClientDedupId(UUID clientDedupId) {
		this.clientDedupId = clientDedupId;
	}
	public String getSchemaYear() {
		return schemaYear;
	}
	public void setSchemaYear(String schemaYear) {
		this.schemaYear = schemaYear;
	}
}