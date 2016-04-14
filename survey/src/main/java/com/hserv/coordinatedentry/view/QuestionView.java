package com.hserv.coordinatedentry.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hserv.coordinatedentry.util.JsonDateSerializer;

public class QuestionView implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Integer questionId;

	/** question_name. */
	private String questionName;

	/** display_text. */
	private String displayText;

	/** question_data_type. */
	private String questionDataType;

	/** question_group_id. */
	private String questionGroupId;

	/** options_single_multiple_select. */
	private Boolean optionsSingleMultipleSelect;

	/** is_copy_question_id. */
	private Boolean isCopyQuestionId;

	/** hud_boolean. */
	private Boolean hudBoolean;

	/** locked. */
	private Boolean locked;

	/** inactive. */
	private Boolean inactive;

	/** label_value. */
	private String labelValue;

	/** date_created. */
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date dateCreated;

	/** date_updated. */
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date dateUpdated;

	/** user_id. */
	private String userId;

	/** survey_id. */
	private String surveyId;

	/** The set of Survey_Question. */
	private List<SurveyQuestionView> surveyQuestionView;

	/**
	 * Constructor.
	 */
	public QuestionView() {
		this.surveyQuestionView = new ArrayList<SurveyQuestionView>();
	}


	/**
	 * Set the question_name.
	 * 
	 * @param questionName
	 *            question_name
	 */
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	/**
	 * Get the question_name.
	 * 
	 * @return question_name
	 */
	public String getQuestionName() {
		return this.questionName;
	}

	/**
	 * Set the display_text.
	 * 
	 * @param displayText
	 *            display_text
	 */
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	/**
	 * Get the display_text.
	 * 
	 * @return display_text
	 */
	public String getDisplayText() {
		return this.displayText;
	}

	/**
	 * Set the question_data_type.
	 * 
	 * @param questionDataType
	 *            question_data_type
	 */
	public void setQuestionDataType(String questionDataType) {
		this.questionDataType = questionDataType;
	}

	/**
	 * Get the question_data_type.
	 * 
	 * @return question_data_type
	 */
	public String getQuestionDataType() {
		return this.questionDataType;
	}

	
	

	public String getQuestionGroupId() {
		return questionGroupId;
	}


	public void setQuestionGroupId(String questionGroupId) {
		this.questionGroupId = questionGroupId;
	}


	public Boolean getOptionsSingleMultipleSelect() {
		return optionsSingleMultipleSelect;
	}


	public void setOptionsSingleMultipleSelect(Boolean optionsSingleMultipleSelect) {
		this.optionsSingleMultipleSelect = optionsSingleMultipleSelect;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public Boolean getIsCopyQuestionId() {
		return isCopyQuestionId;
	}


	public void setIsCopyQuestionId(Boolean isCopyQuestionId) {
		this.isCopyQuestionId = isCopyQuestionId;
	}


	public Boolean getHudBoolean() {
		return hudBoolean;
	}


	public void setHudBoolean(Boolean hudBoolean) {
		this.hudBoolean = hudBoolean;
	}


	public Boolean getLocked() {
		return locked;
	}


	public void setLocked(Boolean locked) {
		this.locked = locked;
	}


	public Boolean getInactive() {
		return inactive;
	}


	public void setInactive(Boolean inactive) {
		this.inactive = inactive;
	}


	/**
	 * Set the label_value.
	 * 
	 * @param labelValue
	 *            label_value
	 */
	public void setLabelValue(String labelValue) {
		this.labelValue = labelValue;
	}

	/**
	 * Get the label_value.
	 * 
	 * @return label_value
	 */
	public String getLabelValue() {
		return this.labelValue;
	}

	/**
	 * Set the date_created.
	 * 
	 * @param dateCreated
	 *            date_created
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * Get the date_created.
	 * 
	 * @return date_created
	 */
	public Date getDateCreated() {
		return this.dateCreated;
	}

	/**
	 * Set the date_updated.
	 * 
	 * @param dateUpdated
	 *            date_updated
	 */
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	/**
	 * Get the date_updated.
	 * 
	 * @return date_updated
	 */
	public Date getDateUpdated() {
		return this.dateUpdated;
	}

	

	/**
	 * Set the survey_id.
	 * 
	 * @param surveyId
	 *            survey_id
	 */
	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}

	/**
	 * Get the survey_id.
	 * 
	 * @return survey_id
	 */
	public String getSurveyId() {
		return this.surveyId;
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


	public Integer getQuestionId() {
		return questionId;
	}


	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}


	public List<SurveyQuestionView> getSurveyQuestion() {
		return surveyQuestionView;
	}


	public void setSurveyQuestion(List<SurveyQuestionView> surveyQuestionView) {
		this.surveyQuestionView = surveyQuestionView;
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
		QuestionView other = (QuestionView) obj;
		if (surveyId == null) {
			if (other.surveyId != null) {
				return false;
			}
		} else if (!surveyId.equals(other.surveyId)) {
			return false;
		}
		return true;
	}

}
