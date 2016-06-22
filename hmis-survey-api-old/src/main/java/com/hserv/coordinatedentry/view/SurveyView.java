package com.hserv.coordinatedentry.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hserv.coordinatedentry.util.JsonDateDeserializer;

public class SurveyView implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Integer surveyId;

	/** survey_title. */
	@NotEmpty(message = "survey.title.not.empty")
	@Length(max = 255, message = "survey.title.max.length")
	private String surveyTitle;

	/** survey_owner. */
	//@NotEmpty(message = "survey.owner.not.empty")
	@Length(max = 255, message = "survey.owner.max.length")
	private String surveyOwner;

	/** date_created. */
	//////@JsonSerialize(using=JsonDateSerializer.class)
	@JsonDeserialize(using=JsonDateDeserializer.class)
	private Date dateCreated;

	/** date_updated. */
	//@JsonSerialize(using=JsonDateSerializer.class)
	@JsonDeserialize(using=JsonDateDeserializer.class)
	private Date dateUpdated;

	/** user_id. */
	//@NotEmpty(message = "survey.userid.not.empty")
	@Length(max = 255, message = "survey.userid.max.length")
	private String userId;

	/** locked. */
	private Boolean locked = Boolean.FALSE;

	/** inactive. */
	private Boolean inactive = Boolean.FALSE;

	/** is_copysuvery_id. */
	private Boolean copySuveryId = Boolean.TRUE;

	/** tag_valuestring. */
	private String tagValue;
	
	/** section_id. */
	//@NotNull(message = "survey.sectionid.not.null")
	//@Min(value = 1, message = "survey.sectionid.min.value")
	private Integer sectionId;

	/** The set of Survey_Question. */
	//@NotEmpty(message = "survey.sectionview.not.empty")
	private List<SurveySectionView> surveySectionView;

	/**
	 * Constructor.
	 */
	public SurveyView() {
		this.surveySectionView = new ArrayList<SurveySectionView>();
	}


	public Integer getSurveyId() {
		return surveyId;
	}


	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}


	/**
	 * Set the survey_title.
	 * 
	 * @param surveyTitle
	 *            survey_title
	 */
	public void setSurveyTitle(String surveyTitle) {
		this.surveyTitle = surveyTitle;
	}

	/**
	 * Get the survey_title.
	 * 
	 * @return survey_title
	 */
	public String getSurveyTitle() {
		return this.surveyTitle;
	}

	/**
	 * Set the survey_owner.
	 * 
	 * @param surveyOwner
	 *            survey_owner
	 */
	public void setSurveyOwner(String surveyOwner) {
		this.surveyOwner = surveyOwner;
	}

	/**
	 * Get the survey_owner.
	 * 
	 * @return survey_owner
	 */
	public String getSurveyOwner() {
		return this.surveyOwner;
	}

	

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}


	

	public Date getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}


	public Date getDateUpdated() {
		return dateUpdated;
	}


	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public Boolean getInactive() {
		return inactive;
	}


	public void setInactive(Boolean inactive) {
		this.inactive = inactive;
	}


	public Boolean getCopySuveryId() {
		return copySuveryId;
	}


	public void setCopySuveryId(Boolean copySuveryId) {
		this.copySuveryId = copySuveryId;
	}


	public String getTagValue() {
		return tagValue;
	}


	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}

	/**
	 * Set the set of the Survey_Question.
	 * 
	 * @param surveyQuestionSet
	 *            The set of Survey_Question
	 */
	public void setSurveySection(List<SurveySectionView> surveySectionView) {
		this.surveySectionView = surveySectionView;
	}

	/**
	 * Add the Survey_Question.
	 * 
	 * @param surveySectionView
	 *            Survey_Question
	 */
	public void addSurveySection(SurveySectionView surveySectionView) {
		this.surveySectionView.add(surveySectionView);
	}

	/**
	 * Get the set of the Survey_Question.
	 * 
	 * @return The set of Survey_Question
	 */
	public List<SurveySectionView> getSurveySection() {
		return this.surveySectionView;
	}

	
	public Integer getSectionId() {
		return sectionId;
	}


	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((surveyId == null) ? 0 : surveyId.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		SurveyView other = (SurveyView) obj;
		if (surveyId == null) {
			if (other.surveyId != null) {
				return false;
			}
		} else if (!surveyId.equals(other.surveyId)) {
			return false;
		}
		return true;
	}


	@Override
	public String toString() {
		return "SurveyView [surveyId=" + surveyId + ", surveyTitle="
				+ surveyTitle + ", surveyOwner=" + surveyOwner
				+ ", dateCreated=" + dateCreated + ", dateUpdated="
				+ dateUpdated + ", userId=" + userId + ", locked=" + locked
				+ ", inactive=" + inactive + ", copySuveryId=" + copySuveryId
				+ ", tagValue=" + tagValue + ", section_id=" + sectionId
				+ ", surveySectionView=" + surveySectionView + "]";
	}

}
