package org.igman92.igman92.entity.impl.user;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.BaseEntity;
import org.igman92.igman92.entity.base.DataSingleKeyEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "security_role")
public class SecurityRole extends DataSingleKeyEntity {

    @NotNull
    @Column
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "LKP_user_type_security_role", joinColumns = @JoinColumn(name = "security_role"), inverseJoinColumns = @JoinColumn(name = "lkp_user_type"))
    private Set<LKPUserType> userTypes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<LKPUserType> getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(Set<LKPUserType> userTypes) {
        this.userTypes = userTypes;
    }
}
