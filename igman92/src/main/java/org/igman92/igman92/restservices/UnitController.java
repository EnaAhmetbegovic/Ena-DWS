package org.igman92.igman92.restservices;

import org.igman92.igman92.dao.IBaseDao;
import org.igman92.igman92.dto.member.MemberDTO;
import org.igman92.igman92.dto.unit.UnitDTO;
import org.igman92.igman92.entity.impl.member.Member;
import org.igman92.igman92.entity.impl.member.MemberScoutGroup;
import org.igman92.igman92.entity.impl.organization.Unit;
import org.igman92.igman92.services.member.MemberService;
import org.igman92.igman92.services.unit.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/unit")
public class UnitController {

    @Autowired
    private UnitService unitService;

    @RequestMapping(value = "/by-scout-group/{scout-group}/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Unit> getUnitsByScoutGroup(@PathVariable("scout-group") Long scoutGroupId) {

        return unitService.getUnitsByScoutGroup(scoutGroupId);
    }

    @RequestMapping(value = "/add/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Unit addNewUnit(@RequestBody Unit unit) {

        return unitService.addNewUnit(unit);
    }

    @RequestMapping(value = "/{unit}/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public UnitDTO getUnitDTO(@PathVariable("unit") Long unitId) {

        return unitService.getUnitDTO(unitId);
    }
}
