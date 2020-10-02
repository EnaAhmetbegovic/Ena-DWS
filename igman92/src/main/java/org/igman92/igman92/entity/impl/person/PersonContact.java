package org.igman92.igman92.entity.impl.person;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.BaseEntity;
import org.igman92.igman92.entity.base.DataSingleKeyEntity;
import org.igman92.igman92.entity.impl.lkp.LKPContactType;

import javax.persistence.*;

@Entity
@Table(name = "person_contact")
public class PersonContact extends DataSingleKeyEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "person")
    private Person person;

    @NotNull
    @Column(name = "value", length = STRING_LENGTH)
    private String value;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "contact_type")
    private LKPContactType contactType;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LKPContactType getContactType() {
        return contactType;
    }

    public void setContactType(LKPContactType contactType) {
        this.contactType = contactType;
    }
}
