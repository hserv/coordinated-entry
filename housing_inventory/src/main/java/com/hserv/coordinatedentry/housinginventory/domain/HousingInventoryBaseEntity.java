package com.hserv.coordinatedentry.housinginventory.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.mariadb.jdbc.internal.common.queryresults.ColumnNameMap;

@MappedSuperclass
public class HousingInventoryBaseEntity {

	@Column(name="date_updated")
	private Date dateUpdated;
	@Column(name="date_created")
	private Date dateCreated;
	@Column(name="inactive")
	private boolean inactive;
	
	public Date getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public boolean isInactive() {
		return inactive;
	}
	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}

}
