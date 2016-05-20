package com.hserv.coordinatedentry.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hserv.coordinatedentry.util.JsonDateSerializer;

@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -3125677940781989368L;

	/** date_created. */
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date dateCreated;

	/** date_updated. */
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date dateUpdated;

	
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

	/**
	 * Method populates system default date on save entity action
	 */
	@PrePersist
	public void populateCreatedDate() {
		dateCreated = new Date();
	}

	/**
	 * Method populates system default date on update entity action
	 */
	@PreUpdate
	public void populateUpdateDate() {
		dateUpdated = new Date();
	}
}
