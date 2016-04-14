package com.hserv.coordinatedentry.housinginventory.web.rest.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.Objects;


/**
 * A DTO for the MatchReservations entity.
 */
public class MatchReservationsDTO implements Serializable {


    private UUID reservationId;


    private String clientId;


    private ZonedDateTime matchDate;


    private Boolean matchStatus;


    private Integer reservationAdult;


    private Integer reservationChildren;


    private Long housingInventoryId;
    private Long eligibleClientId;
    private Long noteId;
   
    
    
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

    public Long getHousingInventoryId() {
        return housingInventoryId;
    }

    public void setHousingInventoryId(Long housingInventoryId) {
        this.housingInventoryId = housingInventoryId;
    }
    public Long getEligibleClientId() {
        return eligibleClientId;
    }

    public void setEligibleClientId(Long eligibleClientId) {
        this.eligibleClientId = eligibleClientId;
    }
    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reservationId == null) ? 0 : reservationId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatchReservationsDTO other = (MatchReservationsDTO) obj;
		if (reservationId == null) {
			if (other.reservationId != null)
				return false;
		} else if (!reservationId.equals(other.reservationId))
			return false;
		return true;
	}
   
    
    
}
