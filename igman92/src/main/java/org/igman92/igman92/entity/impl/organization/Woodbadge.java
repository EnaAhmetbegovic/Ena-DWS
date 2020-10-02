package org.igman92.igman92.entity.impl.organization;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.BaseEntity;
import org.igman92.igman92.entity.base.DataSingleKeyEntity;
import org.igman92.igman92.entity.impl.lkp.organization.LKPWoodbadge;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "woodbadge")
public class Woodbadge extends DataSingleKeyEntity {

    @ManyToOne
    @NotNull
    private LKPWoodbadge woodbadge;

    @NotNull
    @Column(name = "Place")
    private String place;

    @NotNull
    @Column(name = "start_date")
    private Date startDate;

    @NotNull
    @Column(name = "end_date")
    private Date endDate;

    public LKPWoodbadge getWoodbadge() {
        return woodbadge;
    }

    public void setWoodbadge(LKPWoodbadge woodbadge) {
        this.woodbadge = woodbadge;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
