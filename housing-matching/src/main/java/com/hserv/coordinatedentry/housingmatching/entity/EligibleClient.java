package com.hserv.coordinatedentry.housingmatching.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@SuppressWarnings("serial")
@Entity
@Table(name = "eligible_clients", schema = "housing_inventory")
public class EligibleClient extends BaseEntity {
	@Id
	@Column(name = "client_id", unique = true, nullable = false)
	@org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
	private UUID clientId;
	
	
	@JoinColumn(name="client_id",insertable=false,updatable=false)
	@OneToOne(fetch=FetchType.LAZY)
	private Client client;
	
	@Column(name = "survey_type")	
	private String surveyType;
	

	@Column(name = "survey_score")
	private Integer surveyScore;
	

	@Column(name = "program_type")
	private String programType;
	
	@Column(name = "matched")
	private boolean matched;
	
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	@Column(name = "survey_date")
	private LocalDateTime surveyDate;
	
	@Column(name = "spdat_label")
	private String spdatLabel;
		
	@Column(name = "zip_code")
	private String zipCode;
	
	@Column(name="coc_score")
	private Integer cocScore;
	
	@Column(name="client_link")
	private String clientLink;
	
	@Column(name="ignore_match_process")
	private boolean ignoreMatchProcess;
	
	@Column(name="remarks")
	private String remarks;
	
	@OneToMany(mappedBy="eligibleClient",cascade= CascadeType.REMOVE)
	private List<Match> matchs = new ArrayList<>(); 
	
	@Column(name="deleted")
	private boolean deleted;
	
	@Column(name="status")
	private Integer status;
	
	@org.hibernate.annotations.Type(type="pg-uuid")	
	@Column(name="CLIENT_DEDUP_ID")
	private UUID clientDedupId;
	
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	@Column(name = "survey_submission_date")
	private LocalDateTime surveySubmissionDate;
	
	@Column(name="BONUS_SCORE")
	private Integer bonusScore;
	
	@Column(name="TOTAL_SCORE")
	private Integer totalScore;
	
	@Column(name="READDED_REASON")
	private String readdedReason;
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}	

	
	public EligibleClient() {
	}

	public EligibleClient(UUID clientId) {
		this.clientId = clientId;
	}

	public EligibleClient(UUID clientId, String surveyType, Integer surveyScore, String programType,
			boolean matched, LocalDateTime surveyDate, String spdatLabel, Set<Match> matchReservations
			,String zipCode) {
		this.clientId = clientId;
		this.surveyType = surveyType;
		this.surveyScore = surveyScore;
		this.programType = programType;
		this.matched = matched;
		this.surveyDate = surveyDate;
		this.spdatLabel = spdatLabel;
		this.zipCode = zipCode;
	}

	public UUID getClientId() {
		return this.clientId;
	}

	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}


	public String getSurveyType() {
		return this.surveyType;
	}

	public void setSurveyType(String surveyType) {
		this.surveyType = surveyType;
	}


	public Integer getSurveyScore() {
		return this.surveyScore;
	}

	public void setSurveyScore(Integer surveyScore) {
		this.surveyScore = surveyScore;
	}


	public String getProgramType() {
		return this.programType;
	}

	public void setProgramType(String programType) {
		this.programType = programType;
	}


	public boolean getMatched() {
		return this.matched;
	}

	public void setMatched(boolean matched) {
		this.matched = matched;
	}


	public LocalDateTime getSurveyDate() {
		return this.surveyDate;
	}

	public void setSurveyDate(LocalDateTime surveyDate) {
		this.surveyDate = surveyDate;
	}


	public String getSpdatLabel() {
		return this.spdatLabel;
	}

	public void setSpdatLabel(String spdatLabel) {
		this.spdatLabel = spdatLabel;
	}

	//@Transient

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	public List<Match> getMatchs() {
		return matchs;
	}

	public void setMatchs(List<Match> matchs) {
		this.matchs = matchs;
	}
	
	public Integer getCocScore() {
		return cocScore;
	}

	public void setCocScore(Integer cocScore) {
		this.cocScore = cocScore;
	}


	public String getClientLink() {
		return clientLink;
	}

	public void setClientLink(String clientLink) {
		this.clientLink = clientLink;
	}

	public boolean isIgnoreMatchProcess() {
		return ignoreMatchProcess;
	}

	public void setIgnoreMatchProcess(boolean ignoreMatchProcess) {
		this.ignoreMatchProcess = ignoreMatchProcess;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public UUID getClientDedupId() {
		return clientDedupId;
	}

	public void setClientDedupId(UUID clientDedupId) {
		this.clientDedupId = clientDedupId;
	}

	public LocalDateTime getSurveySubmissionDate() {
		return surveySubmissionDate;
	}

	public void setSurveySubmissionDate(LocalDateTime surveySubmissionDate) {
		this.surveySubmissionDate = surveySubmissionDate;
	}

	public Integer getBonusScore() {
		return bonusScore;
	}

	public void setBonusScore(Integer bonusScore) {
		this.bonusScore = bonusScore;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public String getReaddedReason() {
		return readdedReason;
	}

	public void setReaddedReason(String readdedReason) {
		this.readdedReason = readdedReason;
	}
}