package org.igman92.igman92.entity.impl.membership;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.BaseEntity;
import org.igman92.igman92.entity.base.DataSingleKeyEntity;
import org.igman92.igman92.entity.impl.lkp.LKPYear;

import javax.persistence.*;

@Entity
@Table(name = "membership")
public class AssociationMembership extends DataSingleKeyEntity {

    @ManyToOne
    @NotNull
    private LKPYear year;

    @Column(name = "association_membership_fee")
    @NotNull
    private Float associationMembershipFee;

    public LKPYear getYear() {
        return year;
    }

    public void setYear(LKPYear year) {
        this.year = year;
    }

    public Float getAssociationMembershipFee() {
        return associationMembershipFee;
    }

    public void setAssociationMembershipFee(Float associationMembershipFee) {
        this.associationMembershipFee = associationMembershipFee;
    }
}
