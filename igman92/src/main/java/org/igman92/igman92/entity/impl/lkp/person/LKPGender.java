package org.igman92.igman92.entity.impl.lkp.person;

import org.igman92.igman92.entity.base.LookUpEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "LKP_gender", uniqueConstraints = { @UniqueConstraint(columnNames = {"_key"} ) })
public class LKPGender extends LookUpEntity {

    @Column
    private String abbreviation;

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
