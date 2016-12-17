package com.hserv.coordinatedentry.housingmatching.model;

import java.io.IOException;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MatchProcessLog {

	private UUID id;
	private String statusMessage;
	private String step;
	private Object additionalInfo;
	private boolean status;
	private UUID housingUnitId;
	private UUID clientId;
	private UUID projectId;
	private UUID processId;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public Object getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES,true);
			mapper.configure(Feature.ALLOW_SINGLE_QUOTES,true);
			mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
			JsonNode node = mapper.readValue(statusMessage.getBytes(), JsonNode.class);
			this.statusMessage = node.get("statusMessage").asText();
			this.additionalInfo = node.get("additionalInfo");
			this.step = node.get("step").asText();
		} catch (Exception e) {
			System.out.println(statusMessage);
		//	e.printStackTrace();
		}

	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public UUID getHousingUnitId() {
		return housingUnitId;
	}
	public void setHousingUnitId(UUID housingUnitId) {
		this.housingUnitId = housingUnitId;
	}
	public UUID getClientId() {
		return clientId;
	}
	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}
	public UUID getProjectId() {
		return projectId;
	}
	public void setProjectId(UUID projectId) {
		this.projectId = projectId;
	}
	public UUID getProcessId() {
		return processId;
	}
	public void setProcessId(UUID processId) {
		this.processId = processId;
	}
	public Object getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(Object additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
}