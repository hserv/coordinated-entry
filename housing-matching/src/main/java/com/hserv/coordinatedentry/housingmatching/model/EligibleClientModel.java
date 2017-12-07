package com.hserv.coordinatedentry.housingmatching.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hserv.coordinatedentry.housingmatching.annotations.ValidateClient;
import com.hserv.coordinatedentry.housingmatching.enums.SpdatLabelEnum;
import com.servinglynk.hmis.warehouse.core.model.JsonDateDeserializer;
import com.servinglynk.hmis.warehouse.core.model.JsonDateTimeSerializer;
import com.servinglynk.hmis.warehouse.core.model.JsonTimestampDeserializer;
import com.servinglynk.hmis.warehouse.core.model.JsonTimestampSerializer;

@ValidateClient(clientIdField="clientId",linkField="link",clientDedupIdField="clientDedupId")
public class EligibleClientModel {

	private UUID clientId;
	private String surveyType;
	private Integer surveyScore;
	private String programType;
	private Boolean matched;
	@JsonDeserialize(using=JsonDateDeserializer.class)
	@JsonSerialize(using=JsonDateTimeSerializer.class)
	private LocalDateTime surveyDate;
	private SpdatLabelEnum spdatLabel;
	@Length(min=5,max=5,message="Invalid zip code")
	private String zipcode;
	
	@JsonDeserialize(using=JsonTimestampDeserializer.class)
	@JsonSerialize(using=JsonTimestampSerializer.class)
	private LocalDateTime dateCreated;
	@JsonDeserialize(using=JsonTimestampDeserializer.class)
	@JsonSerialize(using=JsonTimestampSerializer.class)
	private LocalDateTime dateUpdated;
	
	@JsonProperty("status")
	private BatchProcessModel batchProcessModel;
	private boolean ignoreMatchProcess;
//	@NotBlank(message="Remarks Required")
	private String remarks;
	private ClientModel client;
	private UUID clientDedupId;
	
	@JsonProperty(access=Access.WRITE_ONLY)
	private String link;
	
	public UUID getClientId() {
		return clientId;
	}
	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}
	public String getSurveyType() {
		return surveyType;
	}
	public void setSurveyType(String surveyType) {
		this.surveyType = surveyType;
	}
	public Integer getSurveyScore() {
		return surveyScore;
	}
	public void setSurveyScore(Integer surveyScore) {
		this.surveyScore = surveyScore;
	}
	public String getProgramType() {
		return programType;
	}
	public void setProgramType(String programType) {
		this.programType = programType;
	}
	public Boolean getMatched() {
		return matched;
	}
	public void setMatched(Boolean matched) {
		this.matched = matched;
	}
	public LocalDateTime getSurveyDate() {
		return surveyDate;
	}
	public void setSurveyDate(LocalDateTime surveyDate) {
		this.surveyDate = surveyDate;
	}
	public SpdatLabelEnum getSpdatLabel() {
		return spdatLabel;
	}
	public boolean isIgnoreMatchProcess() {
		return ignoreMatchProcess;
	}
	public void setIgnoreMatchProcess(boolean ignoreMatchProcess) {
		this.ignoreMatchProcess = ignoreMatchProcess;
	}
	public void setSpdatLabel(SpdatLabelEnum spdatLabel) {
		this.spdatLabel = spdatLabel;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}
	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	public BatchProcessModel getBatchProcessModel() {
		return batchProcessModel;
	}
	public void setBatchProcessModel(BatchProcessModel batchProcessModel) {
		this.batchProcessModel = batchProcessModel;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public ClientModel getClient() {
		return client;
	}
	public void setClient(ClientModel client) {
		this.client = client;
	}
	public UUID getClientDedupId() {
		return clientDedupId;
	}
	public void setClientDedupId(UUID clientDedupId) {
		this.clientDedupId = clientDedupId;
	}
}