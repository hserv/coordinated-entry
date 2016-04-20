package com.hserv.coordinatedentry.housingmatching.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "client_info", schema = "ces")
public class ClientInfo implements java.io.Serializable {

	private UUID clientId;
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
	private UUID userId;
	private EligibleClients eligibleClients;

	public ClientInfo() {
	}

	public ClientInfo(UUID clientId) {
		this.clientId = clientId;
	}

	public ClientInfo(UUID clientId, String firstName, String middleName, String lastName, String nameSuffix,
			String ssn, Date dob, String gender, String otherGender, String ethnicity, String race,
			String contactNumber, String contactEmail, Boolean veteranStatus, Date dateCreated, Date dateUpdated,
			UUID userId, EligibleClients eligibleClients) {
		this.clientId = clientId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.nameSuffix = nameSuffix;
		this.ssn = ssn;
		this.dob = dob;
		this.gender = gender;
		this.otherGender = otherGender;
		this.ethnicity = ethnicity;
		this.race = race;
		this.contactNumber = contactNumber;
		this.contactEmail = contactEmail;
		this.veteranStatus = veteranStatus;
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
		this.userId = userId;
		this.eligibleClients = eligibleClients;
	}

	@Id
	@Column(name = "client_id", unique = true, nullable = false)
	@org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
	public UUID getClientId() {
		return this.clientId;
	}

	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}

	@Column(name = "first_name", length = 50)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "middle_name", length = 50)
	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "last_name", length = 50)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "name_suffix", length = 50)
	public String getNameSuffix() {
		return this.nameSuffix;
	}

	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}

	@Column(name = "ssn", length = 9)
	public String getSsn() {
		return this.ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dob", length = 13)
	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Column(name = "gender")
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "other_gender", length = 10)
	public String getOtherGender() {
		return this.otherGender;
	}

	public void setOtherGender(String otherGender) {
		this.otherGender = otherGender;
	}

	@Column(name = "ethnicity")
	public String getEthnicity() {
		return this.ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	@Column(name = "race")
	public String getRace() {
		return this.race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	@Column(name = "contact_number")
	public String getContactNumber() {
		return this.contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Column(name = "contact_email")
	public String getContactEmail() {
		return this.contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	@Column(name = "veteran_status")
	public Boolean getVeteranStatus() {
		return this.veteranStatus;
	}

	public void setVeteranStatus(Boolean veteranStatus) {
		this.veteranStatus = veteranStatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_created", length = 13)
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_updated", length = 13)
	public Date getDateUpdated() {
		return this.dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	@Column(name = "user_id")
	@org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
	public UUID getUserId() {
		return this.userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "clientInfo")
	public EligibleClients getEligibleClients() {
		return this.eligibleClients;
	}

	public void setEligibleClients(EligibleClients eligibleClients) {
		this.eligibleClients = eligibleClients;
	}

}
