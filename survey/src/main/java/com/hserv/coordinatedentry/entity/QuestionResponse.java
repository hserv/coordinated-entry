package com.hserv.coordinatedentry.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class QuestionResponse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer questionId;
	private Integer pickListId;
	
	
	
	@ManyToOne
	@JoinColumn(name="response_fk_id")
	@JsonBackReference
	private ResponseStorage responseStorage;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getPickListId() {
		return pickListId;
	}

	public void setPickListId(Integer pickListId) {
		this.pickListId = pickListId;
	}

	public ResponseStorage getResponseStorage() {
		return responseStorage;
	}

	public void setResponseStorage(ResponseStorage responseStorage) {
		this.responseStorage = responseStorage;
	}
	
}
