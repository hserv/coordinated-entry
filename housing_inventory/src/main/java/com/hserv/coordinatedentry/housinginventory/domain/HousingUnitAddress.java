package com.hserv.coordinatedentry.housinginventory.domain;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="housing_unit_address",schema="housing_inventory")
public class HousingUnitAddress  extends HousingInventoryBaseEntity {
	
	private static final long serialVersionUID = 1228941176751521786L;

	@Id
	@Column(name="address_id")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private UUID addressId;
	
	@Column(name="addressline_1")
	private String line1;
	
	@Column(name="addressline_2")
	private String line2;
	
	@Column(name="address_state")
	private String state;
	
	@Column(name="address_city")
	private String city;
	
	@Column(name="zip_code")
	private Integer zipCode;
	
	@ManyToOne
	@JsonIgnore
	private HousingInventory housingInventory;
	
	@Transient
	private String housingInventoryId;
	
    public HousingUnitAddress(){
		
	}
	
	public HousingUnitAddress(UUID addressId, String line1, String line2,
			String state, String city, Integer zipCode,HousingInventory housingInventory,  LocalDateTime dateCreated,
			LocalDateTime dateUpdated, Boolean inactive) {
		super(dateCreated, dateUpdated, inactive);
		this.addressId = addressId;
		this.line1 = line1;
		this.line2 = line2;
		this.state = state;
		this.city = city;
		this.zipCode = zipCode;
		this.housingInventory=housingInventory;
	}

	public UUID getAddressId() {
		return addressId;
	}

    public void setAddressId(UUID addressId) {
		this.addressId = addressId;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public HousingInventory getHousingInventory() {
		return housingInventory;
	}

	public void setHousingInventory(HousingInventory housingInventory) {
		this.housingInventory = housingInventory;
	}

	public String getHousingInventoryId() {
		return housingInventoryId;
	}

	public void setHousingInventoryId(String housingInventoryId) {
		this.housingInventoryId = housingInventoryId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((addressId == null) ? 0 : addressId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		HousingUnitAddress other = (HousingUnitAddress) obj;
		if (addressId == null) {
			if (other.addressId != null)
				return false;
		} else if (!addressId.equals(other.addressId))
			return false;
		return true;
	}

	
	
}
