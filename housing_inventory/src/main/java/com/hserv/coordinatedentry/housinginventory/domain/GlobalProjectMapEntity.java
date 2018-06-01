package com.hserv.coordinatedentry.housinginventory.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "hmis_global_project_map",schema="base")
public class GlobalProjectMapEntity {
	
	private UUID id;
	private UUID projectId;
	private String source;
	private GlobalProjectEntity globalProject;
	
	@Id
	@Column( name = "id", nullable = false  )@org.hibernate.annotations.Type(type="pg-uuid")
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	

	@Column( name = "project_id") @org.hibernate.annotations.Type(type="pg-uuid")
	public UUID getProjectId() {
		return projectId;
	}
	public void setProjectId(UUID projectId) {
		this.projectId = projectId;
	}
	

	@Column( name = "source")
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "global_project_id", referencedColumnName="id")
	public GlobalProjectEntity getGlobalProject() {
		return globalProject;
	}
	public void setGlobalProject(GlobalProjectEntity globalProject) {
		this.globalProject = globalProject;
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