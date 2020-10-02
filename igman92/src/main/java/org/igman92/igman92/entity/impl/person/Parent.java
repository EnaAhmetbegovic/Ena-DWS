package org.igman92.igman92.entity.impl.person;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "parent")
public class Parent extends AbstractPerson {

    @OneToMany(mappedBy = "person")
    private List<PersonContact> parentContacts;

    public List<PersonContact> getParentContacts() {
        return parentContacts;
    }

    public void setParentContacts(List<PersonContact> parentContacts) {
        this.parentContacts = parentContacts;
    }
}
