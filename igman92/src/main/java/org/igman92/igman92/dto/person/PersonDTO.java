package org.igman92.igman92.dto.person;

import org.igman92.igman92.entity.impl.person.Person;
import org.igman92.igman92.entity.impl.person.PersonContact;

import java.util.List;

public class PersonDTO {

    private Person person;
    private List<PersonContact> contacts;

    public PersonDTO(){
    }

    public PersonDTO(Person person, List<PersonContact> contacts) {
        this.person = person;
        this.contacts = contacts;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<PersonContact> getContacts() {
        return contacts;
    }

    public void setContacts(List<PersonContact> contacts) {
        this.contacts = contacts;
    }
}
