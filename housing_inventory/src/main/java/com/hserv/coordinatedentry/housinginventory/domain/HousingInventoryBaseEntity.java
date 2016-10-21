package com.hserv.coordinatedentry.housinginventory.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.servinglynk.hmis.warehouse.core.model.JsonDateDeserializer;
import com.servinglynk.hmis.warehouse.core.model.JsonDateTimeSerializer;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class HousingInventoryBaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@CreatedDate
	@Column(name="date_created")
	@JsonDeserialize(using=JsonDateDeserializer.class)	
	@JsonSerialize(using=JsonDateTimeSerializer.class)
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	private LocalDateTime dateCreated;

	@LastModifiedDate
	@Column(name="date_updated")
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	@JsonDeserialize(using=JsonDateDeserializer.class)	
	@JsonSerialize(using=JsonDateTimeSerializer.class)
	private LocalDateTime dateUpdated;

	@CreatedBy
	@Column(name="created_by")
	@JsonIgnore
	private String createdBy;

	@LastModifiedBy
	@Column(name="last_modified_by")
	@JsonIgnore
	private String lastModifiedBy;

	@Column(name="inactive")
	private Boolean inactive;


	public HousingInventoryBaseEntity(){

	}


	public HousingInventoryBaseEntity(LocalDateTime dateCreated, LocalDateTime dateUpdated, Boolean inactive) {
		super();
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
		this.inactive = inactive;
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

	public Boolean getInactive() {
		return inactive;
	}

	public void setInactive(Boolean inactive) {
		this.inactive = inactive;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((dateUpdated == null) ? 0 : dateUpdated.hashCode());
		result = prime * result + ((inactive == null) ? 0 : inactive.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HousingInventoryBaseEntity other = (HousingInventoryBaseEntity) obj;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (dateUpdated == null) {
			if (other.dateUpdated != null)
				return false;
		} else if (!dateUpdated.equals(other.dateUpdated))
			return false;
		if (inactive == null) {
			if (other.inactive != null)
				return false;
		} else if (!inactive.equals(other.inactive))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "HousingInventoryBaseEntity [dateCreated=" + dateCreated + ", dateUpdated=" + dateUpdated + ", inactive="
				+ inactive + ", getDateCreated()=" + getDateCreated() + ", getDateUpdated()=" + getDateUpdated()
				+ ", getInactive()=" + getInactive() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}



}