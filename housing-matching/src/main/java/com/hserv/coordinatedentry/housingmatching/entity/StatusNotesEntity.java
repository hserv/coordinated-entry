package com.hserv.coordinatedentry.housingmatching.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("serial")
@Entity
@Table(name="status_note",schema = "housing_inventory")
public class StatusNotesEntity extends BaseEntity {
	
	@Column(name="id")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @org.hibernate.annotations.Type(type="pg-uuid")
	@Id
	private UUID id;
	
	@Column(name="status_id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	private UUID statusId;
	
	@Column(name="notes")
	private String notes;
	
	@Column(name="deleted")
	private boolean deleted;
	
	@org.hibernate.annotations.Type(type="pg-uuid")	
	@Column(name="CLIENT_DEDUP_ID")
	private UUID clientDedupId;
	
	@Column(name = "client_id", unique = true, nullable = false)
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	private UUID clientId;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getStatusId() {
		return statusId;
	}

	public void setStatusId(UUID statusId) {
		this.statusId = statusId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public UUID getClientDedupId() {
		return clientDedupId;
	}

	public void setClientDedupId(UUID clientDedupId) {
		this.clientDedupId = clientDedupId;
	}

	public UUID getClientId() {
		return clientId;
	}

	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}
}