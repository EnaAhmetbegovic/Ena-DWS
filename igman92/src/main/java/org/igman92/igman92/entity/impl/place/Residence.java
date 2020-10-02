package org.igman92.igman92.entity.impl.place;

import org.igman92.igman92.entity.base.BaseEntity;
import org.igman92.igman92.entity.base.DataSingleKeyEntity;

import javax.persistence.*;

@Entity
@Table(name = "residence")
public class Residence extends DataSingleKeyEntity {

    @Column(name = "street", length = BaseEntity.STRING_LENGTH, nullable = false)
    private String street;

    @ManyToOne
    @JoinColumn(name = "place", nullable = false)
    private Place place;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
