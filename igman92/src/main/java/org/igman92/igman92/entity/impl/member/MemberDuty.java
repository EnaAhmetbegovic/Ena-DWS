package org.igman92.igman92.entity.impl.member;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.BaseEntity;
import org.igman92.igman92.entity.base.DataSingleKeyEntity;
import org.igman92.igman92.entity.impl.lkp.organization.LKPDuty;
import org.igman92.igman92.entity.impl.lkp.organization.LKPUnit;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "member_duty")
public class MemberDuty extends DataSingleKeyEntity {

    @ManyToOne
    @NotNull
    private Member member;

    @ManyToOne
    @NotNull
    private LKPDuty duty;

    @ManyToOne
    @NotNull
    private LKPUnit unit;

    @Column(name = "unit_name")
    private String unitName;

    @NotNull
    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;


}
