package org.igman92.igman92.entity.impl.member;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.BaseEntity;
import org.igman92.igman92.entity.base.DataSingleKeyEntity;
import org.igman92.igman92.entity.impl.lkp.organization.LKPCategory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "member_category")
public class MemberCategory extends DataSingleKeyEntity {

    @ManyToOne
    @NotNull
    private Member member;

    @ManyToOne
    @NotNull
    private LKPCategory category;

    @NotNull
    @Column(name = "joined")
    private Date joined;

    @Column(name = "made_a_promise")
    private Date madeAPromise;

    @Column(name = "place")
    private String place;

    @Column(name = "active")
    private Boolean active;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LKPCategory getCategory() {
        return category;
    }

    public void setCategory(LKPCategory category) {
        this.category = category;
    }

    public Date getJoined() {
        return joined;
    }

    public void setJoined(Date joined) {
        this.joined = joined;
    }

    public Date getMadeAPromise() {
        return madeAPromise;
    }

    public void setMadeAPromise(Date madeAPromise) {
        this.madeAPromise = madeAPromise;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
