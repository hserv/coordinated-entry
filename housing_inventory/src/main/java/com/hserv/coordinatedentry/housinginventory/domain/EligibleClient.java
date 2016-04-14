package com.hserv.coordinatedentry.housinginventory.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A EligibleClient.
 */
@Entity
@Table(name = "eligible_client")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EligibleClient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "client_id")
    private String clientId;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
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

    public Boolean isMatched() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EligibleClient eligibleClient = (EligibleClient) o;
        if(eligibleClient.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, eligibleClient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EligibleClient{" +
            "id=" + id +
            ", clientId='" + clientId + "'" +
            ", spadatScore='" + spadatScore + "'" +
            ", category='" + category + "'" +
            ", matched='" + matched + "'" +
            ", surveyDate='" + surveyDate + "'" +
            ", spdatLevel='" + spdatLevel + "'" +
            '}';
    }
}
