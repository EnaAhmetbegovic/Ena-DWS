package org.igman92.igman92.entity.impl.member;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.BaseEntity;
import org.igman92.igman92.entity.base.DataSingleKeyEntity;
import org.igman92.igman92.entity.impl.organization.ScoutGroup;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "member_scout_group")
public class MemberScoutGroup extends DataSingleKeyEntity {

    @ManyToOne
    @NotNull
    private Member member;

    @ManyToOne
    @NotNull
    private ScoutGroup scoutGroup;

    @Column(name = "member_since")
    private Date memberSince;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public ScoutGroup getScoutGroup() {
        return scoutGroup;
    }

    public void setScoutGroup(ScoutGroup scoutGroup) {
        this.scoutGroup = scoutGroup;
    }

    public Date getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(Date memberSince) {
        this.memberSince = memberSince;
    }
}
