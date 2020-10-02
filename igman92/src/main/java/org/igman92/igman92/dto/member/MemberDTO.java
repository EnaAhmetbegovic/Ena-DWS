package org.igman92.igman92.dto.member;

import org.igman92.igman92.dto.person.GetPersonDTO;
import org.igman92.igman92.entity.impl.lkp.organization.LKPCategory;
import org.igman92.igman92.entity.impl.organization.Activity;
import org.igman92.igman92.entity.impl.organization.ScoutGroup;
import org.igman92.igman92.entity.impl.organization.Unit;

import java.util.Date;
import java.util.List;

public class MemberDTO {

    private GetPersonDTO personDTO;
    private Long memberId;
    private Integer idCardNumber;
    private String recordNumber;
    private ScoutGroup scoutGroup;
    private Date memberSince;
    private List<Unit> units;
    private LKPCategory category;
    private List<Activity> activities;

    public MemberDTO() {}

    public GetPersonDTO getPersonDTO() {
        return personDTO;
    }

    public void setPersonDTO(GetPersonDTO personDTO) {
        this.personDTO = personDTO;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(Integer idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
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

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public LKPCategory getCategory() {
        return category;
    }

    public void setCategory(LKPCategory category) {
        this.category = category;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
