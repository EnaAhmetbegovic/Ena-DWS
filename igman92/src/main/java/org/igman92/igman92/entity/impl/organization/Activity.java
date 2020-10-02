package org.igman92.igman92.entity.impl.organization;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.DataSingleKeyEntity;
import org.igman92.igman92.entity.impl.lkp.organization.LKPActivityType;
import org.igman92.igman92.entity.impl.member.Member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "activity")
public class Activity extends DataSingleKeyEntity {

    @ManyToOne
    @NotNull
    private LKPActivityType activityType;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "start_date")
    @NotNull
    private Date startDate;

    @Column(name = "end_date")
    @NotNull
    private Date endDate;

    @Column(name = "place")
    @NotNull
    private String place;

    @ManyToOne
    private Member head;

    @ManyToOne
    private Member chief;

    @ManyToOne
    private ScoutGroup organizer;

    public LKPActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(LKPActivityType activityType) {
        this.activityType = activityType;
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Member getHead() {
        return head;
    }

    public void setHead(Member head) {
        this.head = head;
    }

    public Member getChief() {
        return chief;
    }

    public void setChief(Member chief) {
        this.chief = chief;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ScoutGroup getOrganizer() {
        return organizer;
    }

    public void setOrganizer(ScoutGroup organizer) {
        this.organizer = organizer;
    }
}
