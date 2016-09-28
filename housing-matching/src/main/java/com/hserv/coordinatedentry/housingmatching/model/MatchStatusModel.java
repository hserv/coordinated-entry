package com.hserv.coordinatedentry.housingmatching.model;

import java.util.Date;
import java.util.UUID;

import com.hserv.coordinatedentry.housingmatching.enums.MatchStatusUpdateEnum;
import com.servinglynk.hmis.warehouse.core.model.Recipients;

public class MatchStatusModel {

	private UUID id;
	private UUID clientId;
	private String status;
	private String comments;
	private Recipients recipients = new Recipients();
	private boolean acitve;
	private Date dateCreated;
	private Date dateUpdated;
	private String userId;
	private String statusCode;

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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
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
	public boolean isAcitve() {
		return acitve;
	}
	public void setAcitve(boolean acitve) {
		this.acitve = acitve;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated) {
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
}