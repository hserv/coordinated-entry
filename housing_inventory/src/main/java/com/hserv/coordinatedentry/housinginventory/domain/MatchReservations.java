package com.hserv.coordinatedentry.housinginventory.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A MatchReservations.
 */
@Entity
@Table(name = "match_reservations")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MatchReservations implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "reservation_id")
    private String reservationId;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "match_date")
    private ZonedDateTime matchDate;

    @Column(name = "match_status")
    private Boolean matchStatus;

    @Column(name = "reservation_adult")
    private Integer reservationAdult;

    @Column(name = "reservation_children")
    private Integer reservationChildren;

    @ManyToOne
    private HousingInventory housingInventory;

    @OneToOne
    @JoinColumn(unique = true)
    private EligibleClient eligibleClient;

    @OneToOne
    @JoinColumn(unique = true)
    private Note note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public ZonedDateTime getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(ZonedDateTime matchDate) {
        this.matchDate = matchDate;
    }

    public Boolean isMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(Boolean matchStatus) {
        this.matchStatus = matchStatus;
    }

    public Integer getReservationAdult() {
        return reservationAdult;
    }

    public void setReservationAdult(Integer reservationAdult) {
        this.reservationAdult = reservationAdult;
    }

    public Integer getReservationChildren() {
        return reservationChildren;
    }

    public void setReservationChildren(Integer reservationChildren) {
        this.reservationChildren = reservationChildren;
    }

    public HousingInventory getHousingInventory() {
        return housingInventory;
    }

    public void setHousingInventory(HousingInventory housingInventory) {
        this.housingInventory = housingInventory;
    }

    public EligibleClient getEligibleClient() {
        return eligibleClient;
    }

    public void setEligibleClient(EligibleClient eligibleClient) {
        this.eligibleClient = eligibleClient;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MatchReservations matchReservations = (MatchReservations) o;
        if(matchReservations.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, matchReservations.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MatchReservations{" +
            "id=" + id +
            ", reservationId='" + reservationId + "'" +
            ", clientId='" + clientId + "'" +
            ", matchDate='" + matchDate + "'" +
            ", matchStatus='" + matchStatus + "'" +
            ", reservationAdult='" + reservationAdult + "'" +
            ", reservationChildren='" + reservationChildren + "'" +
            '}';
    }
}
