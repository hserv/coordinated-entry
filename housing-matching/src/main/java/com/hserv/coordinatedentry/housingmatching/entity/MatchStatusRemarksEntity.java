package com.hserv.coordinatedentry.housingmatching.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "match_status_remarks", schema = "housing_inventory")
public class MatchStatusRemarksEntity extends BaseEntity {
	
	@Column(name="id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	@Id
	private UUID id;
	
	@Column(name="status_code")
	private Long statusCode;
	
	@Column(name="reason")
	private String reason;
	
	@Column(name="deleted")
	private boolean deleted;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public Long getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Long statusCode) {
		this.statusCode = statusCode;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}