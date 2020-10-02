package org.igman92.igman92.dto.unit;

import org.igman92.igman92.entity.impl.member.MemberUnit;
import org.igman92.igman92.entity.impl.organization.Unit;

import java.util.List;

public class UnitDTO {

    private Unit unit;
    private List<MemberUnit> memberUnits;

    public UnitDTO() {}

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public List<MemberUnit> getMemberUnits() {
        return memberUnits;
    }

    public void setMemberUnits(List<MemberUnit> memberUnits) {
        this.memberUnits = memberUnits;
    }
}
