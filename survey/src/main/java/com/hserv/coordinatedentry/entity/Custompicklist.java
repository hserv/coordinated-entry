package com.hserv.coordinatedentry.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the custompicklist database table.
 * 
 */
@Entity
@NamedQuery(name="Custompicklist.findAll", query="SELECT c FROM Custompicklist c")
public class Custompicklist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="picklist_id")
	private Integer picklistId;

	@Temporal(TemporalType.DATE)
	@Column(name="date_created")
	private Date dateCreated;

	@Temporal(TemporalType.DATE)
	@Column(name="date_updated")
	private Date dateUpdated;

	private Boolean inactive;

	@Column(name="is_correct")
	private Boolean isCorrect;

	@Column(name="picklist_key")
	private String picklistKey;

	@Column(name="picklist_value")
	private String picklistValue;

	@Column(name="user_id")
	private String userId;

	//bi-directional many-to-one association to Question
	@ManyToOne
	@JoinColumn(name="question_id")
	private Question question;

	public Custompicklist() {
	}

	public Integer getPicklistId() {
		return this.picklistId;
	}

	public void setPicklistId(Integer picklistId) {
		this.picklistId = picklistId;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return this.dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Boolean getInactive() {
		return this.inactive;
	}

	public void setInactive(Boolean inactive) {
		this.inactive = inactive;
	}

	public Boolean getIsCorrect() {
		return this.isCorrect;
	}

	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public String getPicklistKey() {
		return this.picklistKey;
	}

	public void setPicklistKey(String picklistKey) {
		this.picklistKey = picklistKey;
	}

	public String getPicklistValue() {
		return this.picklistValue;
	}

	public void setPicklistValue(String picklistValue) {
		this.picklistValue = picklistValue;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}