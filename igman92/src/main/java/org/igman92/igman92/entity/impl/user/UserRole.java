package org.igman92.igman92.entity.impl.user;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.BaseEntity;
import org.igman92.igman92.entity.base.DataSingleKeyEntity;
import org.igman92.igman92.entity.impl.member.Member;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserRole extends DataSingleKeyEntity {

    @NotNull
    @ManyToOne
    private Member member;

    @NotNull
    @ManyToOne
    private SecurityUser securityUser;

    @NotNull
    @ManyToOne
    private LKPUserType userType;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public SecurityUser getSecurityUser() {
        return securityUser;
    }

    public void setSecurityUser(SecurityUser securityUser) {
        this.securityUser = securityUser;
    }

    public LKPUserType getUserType() {
        return userType;
    }

    public void setUserType(LKPUserType userType) {
        this.userType = userType;
    }
}
