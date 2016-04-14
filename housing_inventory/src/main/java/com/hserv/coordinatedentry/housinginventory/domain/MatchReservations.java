package com.hserv.coordinatedentry.housinginventory.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A MatchReservations.
 */
@Entity
@Table(name = "match_reservations")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MatchReservations extends HousingInventoryBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    
    @Column(name = "reservation_id")
    private UUID reservationId;

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

	public UUID getReservationId() {
		return reservationId;
	}

	public void setReservationId(UUID reservationId) {
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

	public Boolean getMatchStatus() {
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

    
}
