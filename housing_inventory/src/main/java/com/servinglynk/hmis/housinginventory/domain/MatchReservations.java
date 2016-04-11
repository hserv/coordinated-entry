package com.servinglynk.hmis.housinginventory.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A MatchReservations.
 */
@Entity
@Table(name = "match_reservations")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MatchReservations implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "reservation_id", nullable = false)
    private Long reservationId;
    
    @Column(name = "match_date")
    private LocalDate matchDate;
    
    @Column(name = "match_status")
    private String matchStatus;
    
    @Column(name = "reservation_adults")
    private Long reservationAdults;
    
    @Column(name = "reservation_children")
    private Long reservationChildren;
    
    @Column(name = "manual_match")
    private Boolean manualMatch;
    
    @Column(name = "inactive")
    private Boolean inactive;
    
    @Column(name = "date_created")
    private LocalDate dateCreated;
    
    @Column(name = "date_updated")
    private LocalDate dateUpdated;
    
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "client_id")
    private Long clientId;
    

    
    @Column(name = "housing_unit_id")
    private Long housingUnitId;
    
    @ManyToOne
    @JoinColumn(name = "housing_inventory_id")
    private HousingInventory housingInventory;

    @ManyToOne
    @JoinColumn(name = "note_id")
    private Note note;

    @ManyToOne
    @JoinColumn(name = "client_contact_info_id")
    private ClientContactInfo clientContactInfo;

    @OneToMany(mappedBy = "matchReservations")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UserView> userViews = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReservationId() {
        return reservationId;
    }
    
    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }
    
    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public String getMatchStatus() {
        return matchStatus;
    }
    
    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    public Long getReservationAdults() {
        return reservationAdults;
    }
    
    public void setReservationAdults(Long reservationAdults) {
        this.reservationAdults = reservationAdults;
    }

    public Long getReservationChildren() {
        return reservationChildren;
    }
    
    public void setReservationChildren(Long reservationChildren) {
        this.reservationChildren = reservationChildren;
    }

    public Boolean getManualMatch() {
        return manualMatch;
    }
    
    public void setManualMatch(Boolean manualMatch) {
        this.manualMatch = manualMatch;
    }

    public Boolean getInactive() {
        return inactive;
    }
    
    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }
    
    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateUpdated() {
        return dateUpdated;
    }
    
    public void setDateUpdated(LocalDate dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getClientId() {
        return clientId;
    }
    
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }


    public Long getHousingUnitId() {
        return housingUnitId;
    }
    
    public void setHousingUnitId(Long housingUnitId) {
        this.housingUnitId = housingUnitId;
    }

    public HousingInventory getHousingInventory() {
        return housingInventory;
    }

    public void setHousingInventory(HousingInventory housingInventory) {
        this.housingInventory = housingInventory;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public ClientContactInfo getClientContactInfo() {
        return clientContactInfo;
    }

    public void setClientContactInfo(ClientContactInfo clientContactInfo) {
        this.clientContactInfo = clientContactInfo;
    }

    public Set<UserView> getUserViews() {
        return userViews;
    }

    public void setUserViews(Set<UserView> userViews) {
        this.userViews = userViews;
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
            ", matchDate='" + matchDate + "'" +
            ", matchStatus='" + matchStatus + "'" +
            ", reservationAdults='" + reservationAdults + "'" +
            ", reservationChildren='" + reservationChildren + "'" +
            ", manualMatch='" + manualMatch + "'" +
            ", inactive='" + inactive + "'" +
            ", dateCreated='" + dateCreated + "'" +
            ", dateUpdated='" + dateUpdated + "'" +
            ", userId='" + userId + "'" +
            ", clientId='" + clientId + "'" +

            ", housingUnitId='" + housingUnitId + "'" +
            '}';
    }
}
