package org.igman92.igman92.entity.impl.organization;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.BaseEntity;
import org.igman92.igman92.entity.base.DataSingleKeyEntity;
import org.igman92.igman92.entity.impl.lkp.organization.LKPActivityStatus;
import org.igman92.igman92.entity.impl.lkp.organization.LKPScoutAssociation;
import org.igman92.igman92.entity.impl.lkp.organization.LKPScoutGroup;
import org.igman92.igman92.entity.impl.place.Place;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "scout_group")
public class ScoutGroup extends DataSingleKeyEntity {

    @OneToOne
    @NotNull
    private LKPScoutGroup scoutGroup;

    @ManyToOne
    @NotNull
    private Place place;

    @Column
    @NotNull
    private String address;

    @Column
    private Date founded;

    @OneToOne
    private LKPScoutAssociation scoutAssociation;

    @OneToOne
    @NotNull
    private LKPActivityStatus activityStatus;

    @OneToMany(mappedBy = "scoutGroup")
    private List<ScoutGroupContact> scoutGroupContacts;

    public LKPScoutGroup getScoutGroup() {
        return scoutGroup;
    }

    public void setScoutGroup(LKPScoutGroup scoutGroup) {
        this.scoutGroup = scoutGroup;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getFounded() {
        return founded;
    }

    public void setFounded(Date founded) {
        this.founded = founded;
    }

    public LKPScoutAssociation getScoutAssociation() {
        return scoutAssociation;
    }

    public void setScoutAssociation(LKPScoutAssociation scoutAssociation) {
        this.scoutAssociation = scoutAssociation;
    }

    public LKPActivityStatus getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(LKPActivityStatus activityStatus) {
        this.activityStatus = activityStatus;
    }

    public List<ScoutGroupContact> getScoutGroupContacts() {
        return scoutGroupContacts;
    }

    public void setScoutGroupContacts(List<ScoutGroupContact> scoutGroupContacts) {
        this.scoutGroupContacts = scoutGroupContacts;
    }
}
