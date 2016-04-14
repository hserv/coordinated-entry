package com.hserv.coordinatedentry.housinginventory.web.rest.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


/**
 * A DTO for the ClientInfo entity.
 */
public class ClientInfoDTO implements Serializable {

    private UUID id;

    private String firstName;


    private String middleName;


    private String lastName;


    private String suffix;


    private String ssn;


    private ZonedDateTime dob;


    private String gender;


    private String otherGender;


    private String ethnicity;


    private String race;


    private String contactNumber;


    private String contactEmail;


    private Boolean vateranStatus;


    private String userId;


   
    
    
    public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
    public ZonedDateTime getDob() {
        return dob;
    }

    public void setDob(ZonedDateTime dob) {
        this.dob = dob;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getOtherGender() {
        return otherGender;
    }

    public void setOtherGender(String otherGender) {
        this.otherGender = otherGender;
    }
    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }
    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    public Boolean getVateranStatus() {
        return vateranStatus;
    }

    public void setVateranStatus(Boolean vateranStatus) {
        this.vateranStatus = vateranStatus;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClientInfoDTO clientInfoDTO = (ClientInfoDTO) o;

        if ( ! Objects.equals(id, clientInfoDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ClientInfoDTO{" +
            "id=" + id +
            ", firstName='" + firstName + "'" +
            ", middleName='" + middleName + "'" +
            ", lastName='" + lastName + "'" +
            ", suffix='" + suffix + "'" +
            ", ssn='" + ssn + "'" +
            ", dob='" + dob + "'" +
            ", gender='" + gender + "'" +
            ", otherGender='" + otherGender + "'" +
            ", ethnicity='" + ethnicity + "'" +
            ", race='" + race + "'" +
            ", contactNumber='" + contactNumber + "'" +
            ", contactEmail='" + contactEmail + "'" +
            ", vateranStatus='" + vateranStatus + "'" +
            ", userId='" + userId + "'" +
            '}';
    }
}
