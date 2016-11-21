package com.hserv.coordinatedentry.housingmatching.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.servinglynk.hmis.warehouse.core.model.JsonTimestampDeserializer;
import com.servinglynk.hmis.warehouse.core.model.JsonTimestampSerializer;

@SuppressWarnings("serial")
@Entity
@Table(name="batch_process",schema="housing_inventory")
public class BatchProcessEntity extends BaseEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @org.hibernate.annotations.Type(type="pg-uuid")
	private UUID id;
	
	@Column(name="initiate_by")
	private String initiateBy;
	
	@Column(name="status")
	private String status;
	
	@Column(name="error_message")
	private String errorMessage;
	
	@JsonDeserialize(using=JsonTimestampDeserializer.class)
	@JsonSerialize(using=JsonTimestampSerializer.class)
    @Column(name="started_at")
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	private LocalDateTime startedAt;

	@JsonDeserialize(using=JsonTimestampDeserializer.class)
	@JsonSerialize(using=JsonTimestampSerializer.class)
	@Column(name="completed_at")
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	private LocalDateTime completedAt;

	@Column(name="IS_PROCESSING")
	private Long isProcessing;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getInitiateBy() {
		return initiateBy;
	}
	public void setInitiateBy(String initiateBy) {
		this.initiateBy = initiateBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public LocalDateTime getStartedAt() {
		return startedAt;
	}
	public void setStartedAt(LocalDateTime startedAt) {
		this.startedAt = startedAt;
	}
	public LocalDateTime getCompletedAt() {
		return completedAt;
	}
	public void setCompletedAt(LocalDateTime completedAt) {
		this.completedAt = completedAt;
	}
	public Long getIsProcessing() {
		return isProcessing;
	}
	public void setIsProcessing(Long isProcessing) {
		this.isProcessing = isProcessing;
	}
	
}