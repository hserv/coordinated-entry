package com.servinglynk.hmis.housinginventory.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ClientContactInfo.
 */
@Entity
@Table(name = "client_contact_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ClientContactInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "client_id", nullable = false)
    private Long clientId;
    
    @Column(name = "client_phone_number")
    private String clientPhoneNumber;
    
    @Column(name = "client_email")
    private String clientEmail;
    
    @OneToMany(mappedBy = "clientContactInfo")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MatchReservations> matchReservationss = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }
    
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }
    
    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public String getClientEmail() {
        return clientEmail;
    }
    
    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public Set<MatchReservations> getMatchReservationss() {
        return matchReservationss;
    }

    public void setMatchReservationss(Set<MatchReservations> matchReservationss) {
        this.matchReservationss = matchReservationss;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClientContactInfo clientContactInfo = (ClientContactInfo) o;
        if(clientContactInfo.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, clientContactInfo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ClientContactInfo{" +
            "id=" + id +
            ", clientId='" + clientId + "'" +
            ", clientPhoneNumber='" + clientPhoneNumber + "'" +
            ", clientEmail='" + clientEmail + "'" +
            '}';
    }
}
