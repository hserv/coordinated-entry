package com.hserv.coordinatedentry.housingmatching.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.servinglynk.hmis.warehouse.core.model.Recipients;

public class MatchStatusModel {

	private UUID id;
	private UUID clientId;
	private Integer status;
	private String statusDescription;
	private String comments;
	private Recipients recipients = new Recipients();
	private boolean active;
	
	@JsonSerialize(using=JsonDateTimeSerializer.class)
	@JsonDeserialize(using=JsonDateDeserializer.class)
	private LocalDateTime dateCreated;
	
	@JsonDeserialize(using=JsonDateDeserializer.class)	
	@JsonSerialize(using=JsonDateTimeSerializer.class)
	private LocalDateTime dateUpdated;
	private String userId;
	private String statusCode;
	
	@JsonProperty("note")
	private NoteModel noteModel;
	
	@JsonProperty("notes")
	private List<NoteModel> noteModels = new ArrayList<NoteModel>();

	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getClientId() {
		return clientId;
	}
	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Recipients getRecipients() {
		return recipients;
	}
	public void setRecipients(Recipients recipients) {
		this.recipients = recipients;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public NoteModel getNoteModel() {
		return noteModel;
	}
	public void setNoteModel(NoteModel noteModel) {
		this.noteModel = noteModel;
	}
	public List<NoteModel> getNoteModels() {
		return noteModels;
	}
	public void setNoteModels(List<NoteModel> noteModels) {
		this.noteModels = noteModels;
	}
}