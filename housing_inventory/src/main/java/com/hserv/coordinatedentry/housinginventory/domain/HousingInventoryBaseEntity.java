package com.hserv.coordinatedentry.housinginventory.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class HousingInventoryBaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="date_created")
	private LocalDateTime dateCreated;
	
	@Column(name="date_updated")
	private LocalDateTime dateUpdated;
	
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
	
}