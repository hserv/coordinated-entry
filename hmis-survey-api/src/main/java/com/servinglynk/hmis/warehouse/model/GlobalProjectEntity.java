package com.servinglynk.hmis.warehouse.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "hmis_global_project",schema="base")
public class GlobalProjectEntity  {
	
	private UUID id;
	private String projectName;
	private String projectCommonName;
	private String description;
	private String sourceSystemId;
	
	private List<GlobalProjectMapEntity> projects;
	
	@Id
	@Column( name = "id", nullable = false  ) @org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	

	@Column( name = "project_name")
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	

	@Column( name = "project_common_name") 
	public String getProjectCommonName() {
		return projectCommonName;
	}
	public void setProjectCommonName(String projectCommonName) {
		this.projectCommonName = projectCommonName;
	}
	
	@Column( name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToMany(mappedBy = "globalProject")
	public List<GlobalProjectMapEntity> getProjects() {
		return projects;
	}
	public void setProjects(List<GlobalProjectMapEntity> projects) {
		this.projects = projects;
	}
	
	@Column(name="source_system_id")
	public String getSourceSystemId() {
		return sourceSystemId;
	}
	public void setSourceSystemId(String sourceSystemId) {
		this.sourceSystemId = sourceSystemId;
	}
}