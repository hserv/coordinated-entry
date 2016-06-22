package com.hserv.coordinatedentry.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class SectionScore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer section_score_id;
	private Integer sectionId;
	private float sectionScore;
	
	@ManyToOne
	@JoinColumn(name="response_fk_id")
	@JsonBackReference
	private ResponseStorage responseStorage;
	
	@OneToMany(mappedBy="sectionScore", cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<QuestionResponse> questionResponseList;

	public Integer getSection_score_id() {
		return section_score_id;
	}

	public void setSection_score_id(Integer section_score_id) {
		this.section_score_id = section_score_id;
	}

	public ResponseStorage getResponseStorage() {
		return responseStorage;
	}

	public void setResponseStorage(ResponseStorage responseStorage) {
		this.responseStorage = responseStorage;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public float getSectionScore() {
		return sectionScore;
	}

	public void setSectionScore(float sectionScore) {
		this.sectionScore = sectionScore;
	}
	public List<QuestionResponse> getQuestionResponseList() {
		return questionResponseList;
	}

	public void setQuestionResponseList(List<QuestionResponse> questionResponseList) {
		this.questionResponseList = questionResponseList;
	}
}
