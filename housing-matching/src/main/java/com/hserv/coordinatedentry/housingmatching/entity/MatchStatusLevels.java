package com.hserv.coordinatedentry.housingmatching.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="match_status_levels",schema="housing_inventory")
public class MatchStatusLevels implements Serializable {

	@Id
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@Column(name="id")
	private UUID id;

	@Column(name="project_group_code")
	private String projectGroupCode;
	
	@Column(name="status_code")
	private String statusCode;
	
	@Column(name="status_description")
	private String statusDescription;
	
	@Column(name="next_status_code")
	private String nextStatusCode;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getProjectGroupCode() {
		return projectGroupCode;
	}
	public void setProjectGroupCode(String projectGroupCode) {
		this.projectGroupCode = projectGroupCode;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	public String getNextStatusCode() {
		return nextStatusCode;
	}
	public void setNextStatusCode(String nextStatusCode) {
		this.nextStatusCode = nextStatusCode;
	}
}