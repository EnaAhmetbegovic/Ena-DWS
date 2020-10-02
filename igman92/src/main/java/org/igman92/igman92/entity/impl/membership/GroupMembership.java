package org.igman92.igman92.entity.impl.membership;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.BaseEntity;
import org.igman92.igman92.entity.base.DataSingleKeyEntity;
import org.igman92.igman92.entity.impl.lkp.LKPYear;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "group_membership")
public class GroupMembership extends DataSingleKeyEntity {

    @ManyToOne
    @NotNull
    private LKPYear year;

    @Column(name = "group_membership_fee")
    @NotNull
    private Float groupMembershipFee;

    @ManyToOne
    @NotNull
    private AssociationMembership associationMembership;

    public LKPYear getYear() {
        return year;
    }

    public void setYear(LKPYear year) {
        this.year = year;
    }

    public Float getGroupMembershipFee() {
        return groupMembershipFee;
    }

    public void setGroupMembershipFee(Float groupMembershipFee) {
        this.groupMembershipFee = groupMembershipFee;
    }

    public AssociationMembership getAssociationMembership() {
        return associationMembership;
    }

    public void setAssociationMembership(AssociationMembership associationMembership) {
        this.associationMembership = associationMembership;
    }
}
