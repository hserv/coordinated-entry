package com.hserv.coordinatedentry.housinginventory.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A EligibleClient.
 */
@Entity
@Table(name = "eligible_client")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EligibleClient extends HousingInventoryBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    

    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "spadat_score")
    private Integer spadatScore;

    @Column(name = "category")
    private String category;

    @Column(name = "matched")
    private Boolean matched;

    @Column(name = "survey_date")
    private ZonedDateTime surveyDate;

    @Column(name = "spdat_level")
    private String spdatLevel;

    @OneToOne
    @JoinColumn(unique = true)
    private ClientInfo clientInfo;

	public UUID getClientId() {
		return clientId;
	}

	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}

	public Integer getSpadatScore() {
		return spadatScore;
	}

	public void setSpadatScore(Integer spadatScore) {
		this.spadatScore = spadatScore;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Boolean getMatched() {
		return matched;
	}

	public void setMatched(Boolean matched) {
		this.matched = matched;
	}

	public ZonedDateTime getSurveyDate() {
		return surveyDate;
	}

	public void setSurveyDate(ZonedDateTime surveyDate) {
		this.surveyDate = surveyDate;
	}

	public String getSpdatLevel() {
		return spdatLevel;
	}

	public void setSpdatLevel(String spdatLevel) {
		this.spdatLevel = spdatLevel;
	}

	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

    
}
