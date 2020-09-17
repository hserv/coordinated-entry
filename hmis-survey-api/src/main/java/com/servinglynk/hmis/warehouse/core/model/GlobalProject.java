package com.servinglynk.hmis.warehouse.core.model;

import java.util.UUID;

public class GlobalProject {

	private UUID globalProjectId;
	private String projectName;
	public UUID getGlobalProjectId() {
		return globalProjectId;
	}

	public void setGlobalProjectId(UUID globalProjectId) {
		this.globalProjectId = globalProjectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
