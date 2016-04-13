package com.hserv.coordinatedentry.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hserv.coordinatedentry.util.JsonDateSerializer;

public class SurveyView implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Integer surveyId;

	/** survey_title. */
	private String surveyTitle;

	/** survey_owner. */
	private String surveyOwner;

	/** date_created. */
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date dateCreated;

	/** date_updated. */
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date dateUpdated;

	/** user_id. */
	private String userId;

	/** locked. */
	private Boolean locked;

	/** inactive. */
	private Boolean inactive;

	/** is_copysuvery_id. */
	private Boolean copySuveryId;

	/** tag_valuestring. */
	private String tagValue;
	
	/** section_id. */
	private String section_id;

	/** The set of Survey_Question. */
	private List<SurveyQuestionView> surveyQuestionView;

	/**
	 * Constructor.
	 */
	public SurveyView() {
		this.surveyQuestionView = new ArrayList<SurveyQuestionView>();
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
	public void setSurveyQuestion(List<SurveyQuestionView> surveyQuestionView) {
		this.surveyQuestionView = surveyQuestionView;
	}

	/**
	 * Add the Survey_Question.
	 * 
	 * @param surveyQuestionView
	 *            Survey_Question
	 */
	public void addSurveyQuestion(SurveyQuestionView surveyQuestionView) {
		this.surveyQuestionView.add(surveyQuestionView);
	}

	/**
	 * Get the set of the Survey_Question.
	 * 
	 * @return The set of Survey_Question
	 */
	public List<SurveyQuestionView> getSurveyQuestion() {
		return this.surveyQuestionView;
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


	public String getSection_id() {
		return section_id;
	}


	public void setSection_id(String section_id) {
		this.section_id = section_id;
	}

}
