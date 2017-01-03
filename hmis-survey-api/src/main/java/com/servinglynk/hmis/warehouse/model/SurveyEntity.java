package com.servinglynk.hmis.warehouse.model;

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
import org.hibernate.annotations.Where;


@Entity
@Table(name = "SURVEY",schema="survey")
public class SurveyEntity extends BaseEntity {

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
	@Column(name="PROJECT_GROUP_CODE")	
	private String projectGroupCode;
	@Column(name="LOCKED")	
	private boolean locked;
	@Column(name="IS_COPY_SURVEY_ID")	
	private boolean copySurveyId;
	
	@OneToMany(cascade=CascadeType.REMOVE,mappedBy="surveyEntity",fetch=FetchType.LAZY)
	@Where(clause=" IS_ACTIVE = 'TRUE' ")
	List<SurveySectionEntity> surveySectionEntities = new ArrayList<SurveySectionEntity>();
	
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
	public String getProjectGroupCode() {
		return projectGroupCode;
	}
	public void setProjectGroupCode(String projectGroupCode) {
		this.projectGroupCode = projectGroupCode;
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
}