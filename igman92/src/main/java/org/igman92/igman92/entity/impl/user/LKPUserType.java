package org.igman92.igman92.entity.impl.user;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.LookUpEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "LKP_user_type")
public class LKPUserType extends LookUpEntity {

    @NotNull
    @Column
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "LKP_user_type_security_role", joinColumns = @JoinColumn(name = "lkp_user_type"), inverseJoinColumns = @JoinColumn(name = "security_role"))
    private Set<SecurityRole> roles;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Set<SecurityRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<SecurityRole> roles) {
        this.roles = roles;
    }
}
