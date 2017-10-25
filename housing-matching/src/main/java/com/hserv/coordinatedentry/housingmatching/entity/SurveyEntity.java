package com.hserv.coordinatedentry.housingmatching.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;


@Entity
@Table(name = "SURVEY",schema="survey")
public class SurveyEntity {

	@Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @org.hibernate.annotations.Type(type="pg-uuid")
	@Column(name = "id")
	private UUID id;
	@Column(name="SURVEY_TITLE")
	private String surveyTitle;
	@Column(name="SURVEY_OWNER")	
	private String surveyOwner;
	@Column(name="TAG_VALUE")	
	private String tagValue;

	@Column(name="LOCKED")	
	private boolean locked;
	@Column(name="IS_COPY_SURVEY_ID")	
	private boolean copySurveyId;
	
	@OneToMany(cascade=CascadeType.REMOVE,mappedBy="surveyEntity",fetch=FetchType.LAZY)
	List<SurveySectionEntity> surveySectionEntities = new ArrayList<SurveySectionEntity>();
	
	@Column(name="deleted")
	private boolean deleted;
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}	

	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getSurveyTitle() {
		return surveyTitle;
	}
	public void setSurveyTitle(String surveyTitle) {
		this.surveyTitle = surveyTitle;
	}
	public String getSurveyOwner() {
		return surveyOwner;
	}
	public void setSurveyOwner(String surveyOwner) {
		this.surveyOwner = surveyOwner;
	}
	public String getTagValue() {
		return tagValue;
	}
	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public boolean isCopySurveyId() {
		return copySurveyId;
	}
	public void setCopySurveyId(boolean copySurveyId) {
		this.copySurveyId = copySurveyId;
	}
	public List<SurveySectionEntity> getSurveySectionEntities() {
		return surveySectionEntities;
	}
	public void setSurveySectionEntities(List<SurveySectionEntity> surveySectionEntities) {
		this.surveySectionEntities = surveySectionEntities;
	}
	
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	@Column(name="CREATED_AT")
	protected LocalDateTime createdAt;
	
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	@Column(name="UPDATED_AT")
	protected LocalDateTime updatedAt;
	@Column(name="IS_ACTIVE")	
	protected boolean isActive=true;
	@Column(name="USER_ID")	
	protected String user;
	
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}