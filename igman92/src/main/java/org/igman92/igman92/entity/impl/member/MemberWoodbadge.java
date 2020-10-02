package org.igman92.igman92.entity.impl.member;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.BaseEntity;
import org.igman92.igman92.entity.base.DataSingleKeyEntity;
import org.igman92.igman92.entity.impl.organization.Woodbadge;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "member_woodbadge")
public class MemberWoodbadge extends DataSingleKeyEntity {

    @ManyToOne
    @NotNull
    private Member member;

    @ManyToOne
    @NotNull
    private Woodbadge woodbadge;

    @Column(name = "document_number")
    private String documentNumber;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Woodbadge getWoodbadge() {
        return woodbadge;
    }

    public void setWoodbadge(Woodbadge woodbadge) {
        this.woodbadge = woodbadge;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
}
