package com.hserv.coordinatedentry.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hserv.coordinatedentry.entity.Question;
import com.hserv.coordinatedentry.entity.Survey;
import com.hserv.coordinatedentry.util.JsonDateSerializer;

public class SurveySectionView implements Serializable {
	


	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Integer sectionId;
	
	/** survey_id. */
	private Integer surveyId;

	/** question_id. */
	private Integer questionId;

	/** question_parent. */
	private String questionParent;

	/** question_child. */
	private String questionChild;

	/** required. */
	private String required;

	/** date_created. */
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date dateCreated;

	/** date_updated. */
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date dateUpdated;

	/** user_id. */
	private String userId;
	

	/** Survey. */
	private Survey survey;

	/** Question. */
	private List<QuestionView> questions;

	private String sectionDetail;

	private String sectionText;

	private double sectionWeight;

	/**
	 * Constructor.
	 */
	public SurveySectionView() {
	}

	
	public Integer getSurveyId() {
		return surveyId;
	}


	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}


	public Integer getQuestionId() {
		return questionId;
	}


	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}


	/**
	 * Set the question_parent.
	 * 
	 * @param questionParent
	 *            question_parent
	 */
	public void setQuestionParent(String questionParent) {
		this.questionParent = questionParent;
	}

	/**
	 * Get the question_parent.
	 * 
	 * @return question_parent
	 */
	public String getQuestionParent() {
		return this.questionParent;
	}

	/**
	 * Set the question_child.
	 * 
	 * @param questionChild
	 *            question_child
	 */
	public void setQuestionChild(String questionChild) {
		this.questionChild = questionChild;
	}

	/**
	 * Get the question_child.
	 * 
	 * @return question_child
	 */
	public String getQuestionChild() {
		return this.questionChild;
	}

	/**
	 * Set the required.
	 * 
	 * @param required
	 *            required
	 */
	public void setRequired(String required) {
		this.required = required;
	}

	/**
	 * Get the required.
	 * 
	 * @return required
	 */
	public String getRequired() {
		return this.required;
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
	 * Set the user_id.
	 * 
	 * @param userId
	 *            user_id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Get the user_id.
	 * 
	 * @return user_id
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * Set the Survey.
	 * 
	 * @param survey
	 *            Survey
	 */
	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	/**
	 * Get the Survey.
	 * 
	 * @return Survey
	 */
	public Survey getSurvey() {
		return this.survey;
	}

	/**
	 * Set the Question.
	 * 
	 * @param question
	 *            Question
	 */
	
	public List<QuestionView> getQuestions() {
		return questions;
	}


	public void setQuestions(List<QuestionView> questions) {
		this.questions = questions;
	}
	
	public Integer getSectionId() {
		return sectionId;
	}


	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}


	public String getSectionDetail() {
		return sectionDetail;
	}


	public void setSectionDetail(String sectionDetail) {
		this.sectionDetail = sectionDetail;
	}


	public String getSectionText() {
		return sectionText;
	}


	public void setSectionText(String sectionText) {
		this.sectionText = sectionText;
	}


	public double getSectionWeight() {
		return sectionWeight;
	}


	public void setSectionWeight(double sectionWeight) {
		this.sectionWeight = sectionWeight;
	}


	
}
