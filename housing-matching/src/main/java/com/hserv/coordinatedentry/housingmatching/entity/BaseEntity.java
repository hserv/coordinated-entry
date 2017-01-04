package com.hserv.coordinatedentry.housingmatching.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hserv.coordinatedentry.housingmatching.util.SecurityContextUtil;
import com.servinglynk.hmis.warehouse.core.model.JsonTimestampDeserializer;
import com.servinglynk.hmis.warehouse.core.model.JsonTimestampSerializer;

@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonDeserialize(using=JsonTimestampDeserializer.class)
	@JsonSerialize(using=JsonTimestampSerializer.class)
    @Column(name="date_created")
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	private LocalDateTime dateCreated;

	@JsonDeserialize(using=JsonTimestampDeserializer.class)
	@JsonSerialize(using=JsonTimestampSerializer.class)
	@Column(name="date_updated")
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	private LocalDateTime dateUpdated;
	
	@Column(name="project_group_code")
	private String projectGroupCode;
	
	@Column(name="user_id")
	@org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
	private UUID userId;
	
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
	
	@PrePersist
	protected void onCreate() {
		dateCreated = dateUpdated = LocalDateTime.now();
		if(SecurityContextUtil.getUserAccount()!=null) {
			userId = SecurityContextUtil.getUserAccount().getAccountId();
		}
		if(SecurityContextUtil.getUserProjectGroup()!=null){
			projectGroupCode=SecurityContextUtil.getUserProjectGroup();
		}
	}
	
	@PreUpdate
	protected void onUpdate(){
		dateUpdated = LocalDateTime.now();
		if(SecurityContextUtil.getUserAccount()!=null) {
			userId = SecurityContextUtil.getUserAccount().getAccountId();
		}
	}
	
	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getProjectGroupCode() {
		return projectGroupCode;
	}

	public void setProjectGroupCode(String projectGroupCode) {
		this.projectGroupCode = projectGroupCode;
	}


}