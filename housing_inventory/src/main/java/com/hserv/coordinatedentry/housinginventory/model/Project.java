package com.hserv.coordinatedentry.housinginventory.model;

import java.util.UUID;

public class Project {

	private UUID id;
	private String projectName;
	private String projectCommonName;
	private String description;
	private String sourceSystemId;
	private String schemaYear;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectCommonName() {
		return projectCommonName;
	}
	public void setProjectCommonName(String projectCommonName) {
		this.projectCommonName = projectCommonName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSourceSystemId() {
		return sourceSystemId;
	}
	public void setSourceSystemId(String sourceSystemId) {
		this.sourceSystemId = sourceSystemId;
	}
	public String getSchemaYear() {
		return schemaYear;
	}
	public void setSchemaYear(String schemaYear) {
		this.schemaYear = schemaYear;
	}
}