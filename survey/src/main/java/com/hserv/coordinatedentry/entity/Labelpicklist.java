package com.hserv.coordinatedentry.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the labelpicklist database table.
 * 
 */
@Entity
@NamedQuery(name="Labelpicklist.findAll", query="SELECT l FROM Labelpicklist l")
public class Labelpicklist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String labelindex;

	@Column(name="label_value")
	private String labelValue;

	//bi-directional many-to-one association to Question
	@ManyToOne
	@JoinColumn(name="question_id")
	private Question question;

	public Labelpicklist() {
	}

	public String getLabelindex() {
		return this.labelindex;
	}

	public void setLabelindex(String labelindex) {
		this.labelindex = labelindex;
	}

	public String getLabelValue() {
		return this.labelValue;
	}

	public void setLabelValue(String labelValue) {
		this.labelValue = labelValue;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}