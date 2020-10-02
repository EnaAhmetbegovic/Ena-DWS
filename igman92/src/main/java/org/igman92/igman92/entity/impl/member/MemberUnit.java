package org.igman92.igman92.entity.impl.member;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.BaseEntity;
import org.igman92.igman92.entity.base.DataSingleKeyEntity;
import org.igman92.igman92.entity.impl.organization.Unit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "member_unit")
public class MemberUnit extends DataSingleKeyEntity {

    @ManyToOne
    @NotNull
    private Member member;

    @ManyToOne
    @NotNull
    private Unit unit;

    @Column(name = "joined_unit")
    @NotNull
    private Date joinedUnit;

    @Column(name = "left_unit")
    @NotNull
    private Date leftUnit;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Date getJoinedUnit() {
        return joinedUnit;
    }

    public void setJoinedUnit(Date joinedUnit) {
        this.joinedUnit = joinedUnit;
    }

    public Date getLeftUnit() {
        return leftUnit;
    }

    public void setLeftUnit(Date leftUnit) {
        this.leftUnit = leftUnit;
    }
}
