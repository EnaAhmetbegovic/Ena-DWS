package org.igman92.igman92.entity.impl.lkp;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.LookUpEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "LKP_contact_type", uniqueConstraints = { @UniqueConstraint(columnNames = { "_key" }) })
public class LKPContactType extends LookUpEntity {

    @NotNull
    @Column(name = "personal")
    private Integer personal;

    public Integer getPersonal() {
        return personal;
    }

    public void setPersonal(Integer personal) {
        this.personal = personal;
    }
}
