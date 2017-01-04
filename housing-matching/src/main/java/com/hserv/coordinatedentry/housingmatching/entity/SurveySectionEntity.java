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
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

@Entity
@Table(name="SURVEY_SECTION",schema="survey")
public class SurveySectionEntity {
	
	@Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @org.hibernate.annotations.Type(type="pg-uuid")
	@Column(name = "id")
	private UUID id;
	
	@ManyToOne
	@JoinColumn(name="SURVEY_ID")
	private SurveyEntity surveyEntity;
	
	@Column(name="SECTION_TEXT")
	private String sectionText;
	
	@Column(name="SECTION_DETAIL")
	private String sectionDetail;
	
	@Column(name="SECTION_WEIGHT")
	private int sectionweight;
	
	@Column(name="SECTION_ORDER")
	private int order;
	
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
	public SurveyEntity getSurveyEntity() {
		return surveyEntity;
	}
	public void setSurveyEntity(SurveyEntity surveyEntity) {
		this.surveyEntity = surveyEntity;
	}	
	public String getSectionText() {
		return sectionText;
	}
	public void setSectionText(String sectionText) {
		this.sectionText = sectionText;
	}
	public String getSectionDetail() {
		return sectionDetail;
	}
	public void setSectionDetail(String sectionDetail) {
		this.sectionDetail = sectionDetail;
	}
	public int getSectionweight() {
		return sectionweight;
	}
	public void setSectionweight(int sectionweight) {
		this.sectionweight = sectionweight;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
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