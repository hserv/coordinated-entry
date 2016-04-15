package com.hserv.coordinatedentry.housingmatching.model;

import java.util.Date;
import java.util.UUID;

public class EligibleClientModel {

	private UUID clientId;
	private String firstName;
	private String lastName;
	private int age;
	private int spdatscore;
	private String category;
	private boolean matched;
	private Date surveyDate;
	private String spdatLabel;
	public UUID getClientId() {
		return clientId;
	}
	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSpdatscore() {
		return spdatscore;
	}
	public void setSpdatscore(int spdatscore) {
		this.spdatscore = spdatscore;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public boolean isMatched() {
		return matched;
	}
	public void setMatched(boolean matched) {
		this.matched = matched;
	}
	public Date getSurveyDate() {
		return surveyDate;
	}
	public void setSurveyDate(Date surveyDate) {
		this.surveyDate = surveyDate;
	}
	public String getSpdatLabel() {
		return spdatLabel;
	}
	public void setSpdatLabel(String spdatLabel) {
		this.spdatLabel = spdatLabel;
	}
	
	
}