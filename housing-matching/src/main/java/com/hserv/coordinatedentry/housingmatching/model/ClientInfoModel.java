package com.hserv.coordinatedentry.housingmatching.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ClientInfoModel {

	private String clientId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String nameSuffix;
	private String ssn;
	private Date dob;
	private String gender;
	private String otherGender;
	private String ethnicity;
	private String race;
	private String contactNumber;
	private String contactEmail;
	private Boolean veteranStatus;
	private Date dateCreated;
	private Date dateUpdated;
	private String userId;
	
	private Set<EligibleClientModel> eligibleClients = new HashSet<>(0);
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
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
	public String getNameSuffix() {
		return nameSuffix;
	}
	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getOtherGender() {
		return otherGender;
	}
	public void setOtherGender(String otherGender) {
		this.otherGender = otherGender;
	}
	public String getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public Boolean getVeteranStatus() {
		return veteranStatus;
	}
	public void setVeteranStatus(Boolean veteranStatus) {
		this.veteranStatus = veteranStatus;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Set<EligibleClientModel> getEligibleClients() {
		return eligibleClients;
	}
	public void setEligibleClients(Set<EligibleClientModel> eligibleClients) {
		this.eligibleClients = eligibleClients;
	}
	@Override
	public String toString() {
		return "ClientInfoModel [id=" + clientId + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", nameSuffix=" + nameSuffix + ", ssn=" + ssn + ", dob=" + dob + ", gender=" + gender
				+ ", otherGender=" + otherGender + ", ethnicity=" + ethnicity + ", race=" + race + ", contactNumber="
				+ contactNumber + ", contactEmail=" + contactEmail + ", veteranStatus=" + veteranStatus
				+ ", dateCreated=" + dateCreated + ", dateUpdated=" + dateUpdated + ", userId=" + userId
				+ ", eligibleClients=" + eligibleClients + "]";
	}
	
	
}
