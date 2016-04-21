package com.hserv.coordinatedentry.housingmatching.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "housing_unit_address", schema = "ces")
public class HousingUnitAddress implements java.io.Serializable {

	private UUID addressId;
	private String addressLine1;
	private String addressLine2;
	private String addressCity;
	private String addressState;
	private Integer zipCode;
	private Boolean inactive;
	private Date dateCreated;
	private Date dateUpdated;
	private String userId;
	private Set<HousingInventory> housingInventories = new HashSet<>(0);

	public HousingUnitAddress() {
	}

	public HousingUnitAddress(UUID addressId) {
		this.addressId = addressId;
	}

	public HousingUnitAddress(UUID addressId, String addressLine1, String addressLine2, String addressCity,
			String addressState, Integer zipCode, Boolean inactive, Date dateCreated, Date dateUpdated, String userId,
			Set<HousingInventory> housingInventories) {
		this.addressId = addressId;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressCity = addressCity;
		this.addressState = addressState;
		this.zipCode = zipCode;
		this.inactive = inactive;
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
		this.userId = userId;
		this.housingInventories = housingInventories;
	}

	@Id
	@Column(name = "address_id", unique = true, nullable = false)
	@org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
	public UUID getAddressId() {
		return this.addressId;
	}

	public void setAddressId(UUID addressId) {
		this.addressId = addressId;
	}

	@Column(name = "address_line1")
	public String getAddressLine1() {
		return this.addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	@Column(name = "address_line2")
	public String getAddressLine2() {
		return this.addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	@Column(name = "address_city")
	public String getAddressCity() {
		return this.addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	@Column(name = "address_state")
	public String getAddressState() {
		return this.addressState;
	}

	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}

	@Column(name = "zip_code")
	public Integer getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	@Column(name = "inactive")
	public Boolean getInactive() {
		return this.inactive;
	}

	public void setInactive(Boolean inactive) {
		this.inactive = inactive;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_created", length = 13)
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_updated", length = 13)
	public Date getDateUpdated() {
		return this.dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	@Column(name = "user_id")
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "housingUnitAddress")
	public Set<HousingInventory> getHousingInventories() {
		return this.housingInventories;
	}

	public void setHousingInventories(Set<HousingInventory> housingInventories) {
		this.housingInventories = housingInventories;
	}

}
