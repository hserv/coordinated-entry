package com.servinglynk.hmis.housinginventory.domain;

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
 * A UserView.
 */
@Entity
@Table(name = "user_view")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserView implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "date_viewed")
    private LocalDate dateViewed;
    
    @NotNull
    @Column(name = "reservation_id", nullable = false)
    private Long reservationId;
    
    @ManyToOne
    @JoinColumn(name = "match_reservations_id")
    private MatchReservations matchReservations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDateViewed() {
        return dateViewed;
    }
    
    public void setDateViewed(LocalDate dateViewed) {
        this.dateViewed = dateViewed;
    }

    public Long getReservationId() {
        return reservationId;
    }
    
    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public MatchReservations getMatchReservations() {
        return matchReservations;
    }

    public void setMatchReservations(MatchReservations matchReservations) {
        this.matchReservations = matchReservations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserView userView = (UserView) o;
        if(userView.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, userView.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UserView{" +
            "id=" + id +
            ", userId='" + userId + "'" +
            ", dateViewed='" + dateViewed + "'" +
            ", reservationId='" + reservationId + "'" +
            '}';
    }
}
