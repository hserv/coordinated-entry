package com.hserv.coordinatedentry.housingmatching.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "hmis_global_project",schema="base")
public class GlobalProjectEntity {
	
	private UUID id;
	private String projectName;
	private String projectCommonName;
	private String description;
	private String sourceSystemId;
	
	private List<GlobalProjectMapEntity> projects;
	
	@Id
	@Column( name = "id", nullable = false  ) @org.hibernate.annotations.Type(type="pg-uuid")
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
	
	@OneToMany(mappedBy = "globalProject",fetch=FetchType.LAZY)
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
		
	@Column(name="project_group_code")
	private String projectGroupCode;
	
	@Column(name="user_id")
	@org.hibernate.annotations.Type(type="pg-uuid")
	private UUID userId;

	@Column(name="deleted")
	private boolean deleted;
	
	
	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getProjectGroupCode() {
		return projectGroupCode;
	}

	public void setProjectGroupCode(String projectGroupCode) {
		this.projectGroupCode = projectGroupCode;
	}	
	

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}