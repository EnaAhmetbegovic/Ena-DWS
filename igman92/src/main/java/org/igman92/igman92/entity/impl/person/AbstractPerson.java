package org.igman92.igman92.entity.impl.person;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.BaseEntity;
import org.igman92.igman92.entity.base.DataSingleKeyEntity;
import org.igman92.igman92.entity.impl.lkp.person.LKPGender;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class AbstractPerson extends DataSingleKeyEntity {

    @NotNull
    @Column(name = "first_name", length = STRING_LENGTH)
    private String firstName;

    @NotNull
    @Column(name = "last_name", length = STRING_LENGTH)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "gender")
    private LKPGender gender;

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
}
