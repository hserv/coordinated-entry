package com.hserv.coordinatedentry.housingmatching.entity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="housing_unit",schema="housing_inventory")
public class HousingInventory implements Serializable {
	
	private static final long serialVersionUID = 4758637074635988540L;

	@Id
	@Column(name = "housing_unit_id")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @org.hibernate.annotations.Type(type="pg-uuid")
	private UUID housingInventoryId;
	
	@Column(name = "beds_current")
	private Integer bedsCurrent;
	
	@Column(name = "project_id")
    @org.hibernate.annotations.Type(type="pg-uuid")
	private UUID projectId;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "beds_capacity")
	private Integer bedsCapacity;
	
	@Column(name = "family_unit")
	private Boolean familyUnit;
	
	@Column(name = "in_service")
	private Boolean inService;
	
	@Column(name = "vacant")
	private Boolean vacant=true;
	
	@Column(name="alias_name")
	private String aliasName;
	
	@Column(name="schema_year")
	private Integer schemaYear;
	
	@Column(name="project_group_code")
	private String projectGroupCode;
	
	@CreatedDate
	@Column(name="date_created")
	@JsonFormat(pattern="MM-dd-yyyy HH:mm:ss")
	@JsonIgnore
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	private LocalDateTime dateCreated;

	@LastModifiedDate
	@Column(name="date_updated")
	@JsonFormat(pattern="MM-dd-yyyy HH:mm:ss")	
	@JsonIgnore
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
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
		
	public UUID getHousingInventoryId() {
		return housingInventoryId;
	}

	public void setHousingInventoryId(UUID housingInventoryId) {
		this.housingInventoryId = housingInventoryId;
	}

	public Integer getBedsCurrent() {
		return bedsCurrent;
	}

	public void setBedsCurrent(Integer bedsCurrent) {
		this.bedsCurrent = bedsCurrent;
	}

	

	public Integer getBedsCapacity() {
		return bedsCapacity;
	}

	public void setBedsCapacity(Integer bedsCapacity) {
		this.bedsCapacity = bedsCapacity;
	}

	public Boolean getFamilyUnit() {
		return familyUnit;
	}

	public void setFamilyUnit(Boolean familyUnit) {
		this.familyUnit = familyUnit;
	}

	public Boolean getInService() {
		return inService;
	}

	public void setInService(Boolean inService) {
		this.inService = inService;
	}

	public Boolean getVacant() {
		return vacant;
	}

	public void setVacant(Boolean vacant) {
		this.vacant = vacant;
	}

	public UUID getProjectId() {
		return projectId;
	}

	public void setProjectId(UUID projectId) {
		this.projectId = projectId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public Integer getSchemaYear() {
		return schemaYear;
	}

	public void setSchemaYear(Integer schemaYear) {
		this.schemaYear = schemaYear;
	}

	public String getProjectGroupCode() {
		return projectGroupCode;
	}

	public void setProjectGroupCode(String projectGroupCode) {
		this.projectGroupCode = projectGroupCode;
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
		int result = super.hashCode();
		result = prime * result + ((housingInventoryId == null) ? 0 : housingInventoryId.hashCode());
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
		HousingInventory other = (HousingInventory) obj;
		if (housingInventoryId == null) {
			if (other.housingInventoryId != null)
				return false;
		} else if (!housingInventoryId.equals(other.housingInventoryId))
			return false;
		return true;
	}

	
	
	

}