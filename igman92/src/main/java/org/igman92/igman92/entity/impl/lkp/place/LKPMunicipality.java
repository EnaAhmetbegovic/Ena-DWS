package org.igman92.igman92.entity.impl.lkp.place;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.LookUpEntity;

import javax.persistence.*;

@Entity
@Table(name = "LKP_municipality", uniqueConstraints = { @UniqueConstraint(columnNames = { "_key" }) })
public class LKPMunicipality extends LookUpEntity {

    @ManyToOne
    @JoinColumn(name = "canton")
    private LKPCanton canton;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "country")
    private LKPCountry country;

    @ManyToOne
    @JoinColumn(name = "entity")
    private LKPEntity entity;

    public LKPCanton getCanton() {
        return canton;
    }

    public void setCanton(LKPCanton canton) {
        this.canton = canton;
    }

    public LKPCountry getCountry() {
        return country;
    }

    public void setCountry(LKPCountry country) {
        this.country = country;
    }

    public LKPEntity getEntity() {
        return entity;
    }

    public void setEntity(LKPEntity entity) {
        this.entity = entity;
    }
}
