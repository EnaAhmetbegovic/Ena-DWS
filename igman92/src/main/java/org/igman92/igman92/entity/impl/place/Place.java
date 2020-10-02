package org.igman92.igman92.entity.impl.place;

import org.igman92.igman92.entity.base.BaseEntity;
import org.igman92.igman92.entity.base.DataSingleKeyEntity;
import org.igman92.igman92.entity.impl.lkp.place.LKPCanton;
import org.igman92.igman92.entity.impl.lkp.place.LKPCountry;
import org.igman92.igman92.entity.impl.lkp.place.LKPEntity;
import org.igman92.igman92.entity.impl.lkp.place.LKPMunicipality;

import javax.persistence.*;

@Entity
@Table(name = "Place")
public class Place extends DataSingleKeyEntity {

    @Column(name = "name", length = STRING_LENGTH, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "municipality")
    private LKPMunicipality municipality;

    @ManyToOne
    @JoinColumn(name = "canton")
    private LKPCanton canton;

    @ManyToOne
    @JoinColumn(name = "entity")
    private LKPEntity entity;

    @ManyToOne
    @JoinColumn(name = "country")
    private LKPCountry country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LKPMunicipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(LKPMunicipality municipality) {
        this.municipality = municipality;
    }

    public LKPCanton getCanton() {
        return canton;
    }

    public void setCanton(LKPCanton canton) {
        this.canton = canton;
    }

    public LKPEntity getEntity() {
        return entity;
    }

    public void setEntity(LKPEntity entity) {
        this.entity = entity;
    }

    public LKPCountry getCountry() {
        return country;
    }

    public void setCountry(LKPCountry country) {
        this.country = country;
    }
}
