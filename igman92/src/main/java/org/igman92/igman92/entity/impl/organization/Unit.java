package org.igman92.igman92.entity.impl.organization;

import com.sun.istack.NotNull;
import org.igman92.igman92.entity.base.DataSingleKeyEntity;
import org.igman92.igman92.entity.impl.lkp.organization.LKPUnit;
import org.igman92.igman92.entity.impl.member.Member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "unit")
public class Unit extends DataSingleKeyEntity {

    @ManyToOne
    @NotNull
    private LKPUnit unit;

    @NotNull
    @Column(name = "unit_name")
    private String unitName;

    @NotNull
    @ManyToOne
    private Member leader;

    @NotNull
    @Column(name = "founded")
    private Date founded;

    @NotNull
    @ManyToOne
    private ScoutGroup scoutGroup;

    public LKPUnit getUnit() {
        return unit;
    }

    public void setUnit(LKPUnit unit) {
        this.unit = unit;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Member getLeader() {
        return leader;
    }

    public void setLeader(Member leader) {
        this.leader = leader;
    }

    public Date getFounded() {
        return founded;
    }

    public void setFounded(Date founded) {
        this.founded = founded;
    }

    public ScoutGroup getScoutGroup() {
        return scoutGroup;
    }

    public void setScoutGroup(ScoutGroup scoutGroup) {
        this.scoutGroup = scoutGroup;
    }
}
