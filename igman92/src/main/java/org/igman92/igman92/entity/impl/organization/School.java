package org.igman92.igman92.entity.impl.organization;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.BaseEntity;
import org.igman92.igman92.entity.base.DataSingleKeyEntity;
import org.igman92.igman92.entity.impl.lkp.organization.LKPSchoolType;
import org.igman92.igman92.entity.impl.member.Member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "school")
public class School extends DataSingleKeyEntity {

    @ManyToOne
    @NotNull
    private LKPSchoolType schoolType;

    @NotNull
    @Column(name = "Place")
    private String place;

    @NotNull
    @Column(name = "start_date")
    private Date startDate;

    @NotNull
    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne
    @NotNull
    private Member head;

    @ManyToOne
    @NotNull
    private Member chief;

    public LKPSchoolType getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(LKPSchoolType schoolType) {
        this.schoolType = schoolType;
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
}
