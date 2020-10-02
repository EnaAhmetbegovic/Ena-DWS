package org.igman92.igman92.dto.person;

import org.igman92.igman92.entity.impl.lkp.person.LKPBloodType;
import org.igman92.igman92.entity.impl.lkp.person.LKPGender;
import org.igman92.igman92.entity.impl.lkp.person.LKPNationality;
import org.igman92.igman92.entity.impl.person.PersonContact;
import org.igman92.igman92.entity.impl.place.Place;
import org.igman92.igman92.entity.impl.place.Residence;

import java.util.Date;
import java.util.List;

public class GetPersonDTO {

    private Long personId;
    private String firstName;
    private String lastName;
    private LKPGender gender;
    private String jmbg;
    private String fathersFullName;
    private String mothersFullName;
    private Date dateOfBirth;
    private Place placeOfBirth;
    private LKPNationality nationality;
    private Residence residence;
    private LKPBloodType bloodType;

    public GetPersonDTO() {}

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LKPGender getGender() {
        return gender;
    }

    public void setGender(LKPGender gender) {
        this.gender = gender;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getFathersFullName() {
        return fathersFullName;
    }

    public void setFathersFullName(String fathersFullName) {
        this.fathersFullName = fathersFullName;
    }

    public String getMothersFullName() {
        return mothersFullName;
    }

    public void setMothersFullName(String mothersFullName) {
        this.mothersFullName = mothersFullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Place getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(Place placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public LKPNationality getNationality() {
        return nationality;
    }

    public void setNationality(LKPNationality nationality) {
        this.nationality = nationality;
    }

    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }

    public LKPBloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(LKPBloodType bloodType) {
        this.bloodType = bloodType;
    }
}
