package org.igman92.igman92.entity.impl.lkp.place;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.BaseEntity;
import org.igman92.igman92.entity.base.LookUpEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "LKP_country", uniqueConstraints = { @UniqueConstraint(columnNames = { "_key" }) })
public class LKPCountry extends LookUpEntity {

    @NotNull
    @Column(name = "official_name", length = BaseEntity.STRING_LENGTH)
    private String officialName;

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }
}
