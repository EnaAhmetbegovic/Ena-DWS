package org.igman92.igman92.services.unit;

import org.igman92.igman92.dao.IBaseDao;
import org.igman92.igman92.dao.exceptions.NoEntityFoundException;
import org.igman92.igman92.dao.exceptions.ProjectException;
import org.igman92.igman92.dto.person.PersonDTO;
import org.igman92.igman92.dto.unit.UnitDTO;
import org.igman92.igman92.entity.impl.member.Member;
import org.igman92.igman92.entity.impl.member.MemberUnit;
import org.igman92.igman92.entity.impl.organization.Unit;
import org.igman92.igman92.entity.impl.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class UnitService {

    private IBaseDao<Unit> unitDao;
    private IBaseDao<MemberUnit> memberUnitDao;

    @Autowired
    public void setUnitDao(IBaseDao<Unit> daoToSet) {
        unitDao = daoToSet;
        unitDao.setClazz(Unit.class);
    }

    @Autowired
    public void setMemberUnitDao(IBaseDao<MemberUnit> daoToSet) {
        memberUnitDao = daoToSet;
        memberUnitDao.setClazz(MemberUnit.class);
    }

    @Transactional(readOnly = true)
    public List<Unit> getUnitsByScoutGroup(Long scoutGroupId) {

        if (scoutGroupId == null) {
            throw new IllegalArgumentException("Scout group id must be specified!");
        }

        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("scoutGroupId", scoutGroupId);

            String query = "SELECT u FROM Unit u " +
                    "WHERE u.scoutGroup.id = :scoutGroupId";

            return unitDao.query(query, params);

        } catch (NoEntityFoundException e) {
            return Collections.emptyList();
        } catch (Exception e) {
            throw new ProjectException("Could not get members by scout group! " + e);
        }
    }

    @Transactional
    public Unit addNewUnit(Unit unit) {

        if (unit == null) {
            throw new ProjectException("Unit parameters must be specified!");
        }

        try {
            Unit newUnit = new Unit();

            newUnit.setFounded(unit.getFounded());
            newUnit.setLeader(unit.getLeader());
            newUnit.setScoutGroup(unit.getScoutGroup());
            newUnit.setUnit(unit.getUnit());
            newUnit.setUnitName(unit.getUnitName());

            unitDao.save(newUnit);

            return newUnit;

        } catch (Exception e) {
            throw new ProjectException("Could not save new unit! " + e);
        }
    }

    @Transactional(readOnly = true)
    public UnitDTO getUnitDTO(Long unitId) {

        if (unitId == null) {
            throw new ProjectException("Unit id must be specified!");
        }

        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("unitId", unitId);

            String query = "SELECT mu FROM Unit u " +
                    "INNER JOIN MemberUnit mu ON u.id = mu.unit.id " +
                    "WHERE u.id = :unitId";

            List<MemberUnit> memberUnits = new ArrayList<>();

            try {
                memberUnits = memberUnitDao.query(query, params);
            } catch (NoEntityFoundException e) {
                memberUnits = Collections.emptyList();
            }

            UnitDTO unitDTO = new UnitDTO();

            unitDTO.setUnit(unitDao.getById(unitId));
            unitDTO.setMemberUnits(memberUnits);

            return unitDTO;

        } catch (Exception e) {
            throw new ProjectException("Could not get unitDTO! " + e);
        }
    }
}
