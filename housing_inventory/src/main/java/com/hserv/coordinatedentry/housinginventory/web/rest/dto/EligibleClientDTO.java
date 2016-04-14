package com.hserv.coordinatedentry.housinginventory.web.rest.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.Objects;


/**
 * A DTO for the EligibleClient entity.
 */
public class EligibleClientDTO implements Serializable {

    

    private UUID clientId;


    private Integer spadatScore;


    private String category;


    private Boolean matched;


    private ZonedDateTime surveyDate;


    private String spdatLevel;


    private Long clientInfoId;
   
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

    public Long getClientInfoId() {
        return clientInfoId;
    }

    public void setClientInfoId(Long clientInfoId) {
        this.clientInfoId = clientInfoId;
    }
   
   
}
