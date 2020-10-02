package org.igman92.igman92.entity.impl.organization;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.BaseEntity;
import org.igman92.igman92.entity.base.DataSingleKeyEntity;
import org.igman92.igman92.entity.impl.lkp.LKPContactType;

import javax.persistence.*;

@Entity
@Table(name = "organization_contact")
public class ScoutGroupContact extends DataSingleKeyEntity {

    @ManyToOne
    @NotNull
    @JoinColumn(name = "scout_group")
    private ScoutGroup scoutGroup;

    @NotNull
    @Column(name = "value", length = STRING_LENGTH)
    private String value;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "contact_type")
    private LKPContactType contactType;
}
