package com.hserv.coordinatedentry.housingmatching.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "match_status", schema = "housing_inventory")
public class MatchStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private UUID id;
	
	@Column(name = "client_id", unique = true, nullable = false)
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	private UUID clientId;
	
	@Column(name="status")
	private Integer status;
	
	@Column(name="comments")
	private String comments;
	
	@Column(name="recipients")
	private String recipients;
	
	@Column(name="active")
	private boolean acitve;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_created")
	private Date dateCreated;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_updated")
	private Date dateUpdated;
	
	@Column(name="user_id")
	private String userId;
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
	public String getRecipients() {
		return recipients;
	}
	public void setRecipients(String recipients) {
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
	
	@PrePersist
	protected void onCreate() {
		dateCreated = dateUpdated = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		dateUpdated = new Date();
	}
}