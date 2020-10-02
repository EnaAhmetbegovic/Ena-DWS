package org.igman92.igman92.entity.impl.person;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.impl.lkp.person.LKPBloodType;
import org.igman92.igman92.entity.impl.lkp.person.LKPNationality;
import org.igman92.igman92.entity.impl.place.Place;
import org.igman92.igman92.entity.impl.place.Residence;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "person")
public class Person extends AbstractPerson {

    @Column(name = "jmbg")
    @NotNull
    private String jmbg;

    @ManyToOne
    @JoinColumn(name = "father")
    private Parent father;

    @ManyToOne
    @JoinColumn(name = "mother")
    private Parent mother;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "place_of_birth")
    private Place placeOfBirth;

    @ManyToOne
    @JoinColumn(name = "nationality")
    private LKPNationality nationality;

    @ManyToOne
    @JoinColumn(name = "residence")
    private Residence residence;

//    @OneToMany(mappedBy = "person")
//    private List<PersonContact> contacts;

    @ManyToOne
    @JoinColumn(name = "blood_type")
    private LKPBloodType bloodType;

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public Parent getFather() {
        return father;
    }

    public void setFather(Parent father) {
        this.father = father;
    }

    public Parent getMother() {
        return mother;
    }

    public void setMother(Parent mother) {
        this.mother = mother;
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

//    public List<PersonContact> getContacts() {
//        return contacts;
//    }
//
//    public void setContacts(List<PersonContact> contacts) {
//        this.contacts = contacts;
//    }

    public LKPBloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(LKPBloodType bloodType) {
        this.bloodType = bloodType;
    }
}