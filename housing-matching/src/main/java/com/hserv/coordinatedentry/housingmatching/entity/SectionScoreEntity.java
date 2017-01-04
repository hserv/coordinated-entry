package com.hserv.coordinatedentry.housingmatching.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "SECTION_SCORE",schema="survey")
public class SectionScoreEntity {

	@Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @org.hibernate.annotations.Type(type="pg-uuid")
	@Column(name = "id")
	private UUID id;
	
	@Column(name="SECTION_SCORE")
	private int sectionScore;
	
	@ManyToOne
	@JoinColumn(name="SECTION_ID")
	private SurveySectionEntity sectionEntity;
	
	@ManyToOne
	@JoinColumn(name="SURVEY_ID")
	private SurveyEntity surveyEntity;
	
	@org.hibernate.annotations.Type(type="pg-uuid")
	@Column(name="CLIENT_ID")
	private UUID clientId;
	
	@Column(name="deleted")
	private boolean deleted;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public int getSectionScore() {
		return sectionScore;
	}
	public void setSectionScore(int sectionScore) {
		this.sectionScore = sectionScore;
	}
	public SurveySectionEntity getSectionEntity() {
		return sectionEntity;
	}
	public void setSectionEntity(SurveySectionEntity sectionEntity) {
		this.sectionEntity = sectionEntity;
	}
	public SurveyEntity getSurveyEntity() {
		return surveyEntity;
	}
	public void setSurveyEntity(SurveyEntity surveyEntity) {
		this.surveyEntity = surveyEntity;
	}
	public UUID getClientId() {
		return clientId;
	}
	public void setClientId(UUID clientId) {
		this.clientId = clientId;
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
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}