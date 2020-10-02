package org.igman92.igman92.entity.impl.member;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.BaseEntity;
import org.igman92.igman92.entity.base.DataSingleKeyEntity;
import org.igman92.igman92.entity.impl.lkp.organization.LKPMeritBadge;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
public class MemberMeritBadge extends DataSingleKeyEntity {

    @ManyToOne
    @NotNull
    private Member member;

    @ManyToOne
    @NotNull
    private LKPMeritBadge meritBadge;

    @Column
    @NotNull
    private Date date;

    @Column
    @NotNull
    private String place;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LKPMeritBadge getMeritBadge() {
        return meritBadge;
    }

    public void setMeritBadge(LKPMeritBadge meritBadge) {
        this.meritBadge = meritBadge;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
