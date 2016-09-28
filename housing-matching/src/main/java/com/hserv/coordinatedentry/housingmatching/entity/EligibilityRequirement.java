package com.hserv.coordinatedentry.housingmatching.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="housing_unit_eligibility",schema="housing_inventory")
public class EligibilityRequirement implements Serializable {

	private static final long serialVersionUID = 4217545997779297226L;

	@Id
	@Column(name = "eligibility_id")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @org.hibernate.annotations.Type(type="pg-uuid")
	private UUID eligibilityId;
		
	@Column(name="project_id")
    @org.hibernate.annotations.Type(type="pg-uuid")
	private UUID projectId;
	
	@Column(name="eligibility")
	private String eligibility;
	
	@Column(name="project_group_code")
	private String projectGroupCode;
	
	@CreatedDate
	@Column(name="date_created")
	@JsonFormat(pattern="MM-dd-yyyy HH:mm:ss")
	@JsonIgnore
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	private LocalDateTime dateCreated;

	@LastModifiedDate
	@Column(name="date_updated")
	@JsonFormat(pattern="MM-dd-yyyy HH:mm:ss")	
	@JsonIgnore
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	private LocalDateTime dateUpdated;

	@CreatedBy
	@Column(name="created_by")
	@JsonIgnore
	private String createdBy;

	@LastModifiedBy
	@Column(name="last_modified_by")
	@JsonIgnore
	private String lastModifiedBy;

	@Column(name="inactive")
	private Boolean inactive;	
	
	
	public UUID getEligibilityId() {
		return eligibilityId;
	}
	public void setEligibilityId(UUID eligibilityId) {
		this.eligibilityId = eligibilityId;
	}
	public UUID getProjectId() {
		return projectId;
	}
	public void setProjectId(UUID projectId) {
		this.projectId = projectId;
	}
	public String getEligibility() {
		return eligibility;
	}
	public void setEligibility(String eligibility) {
		this.eligibility = eligibility;
	}
	public String getProjectGroupCode() {
		return projectGroupCode;
	}
	public void setProjectGroupCode(String projectGroupCode) {
		this.projectGroupCode = projectGroupCode;
	}	
	
	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Boolean getInactive() {
		return inactive;
	}

	public void setInactive(Boolean inactive) {
		this.inactive = inactive;
	}
}