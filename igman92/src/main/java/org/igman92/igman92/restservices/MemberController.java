package org.igman92.igman92.restservices;

import org.igman92.igman92.dto.member.MemberDTO;
import org.igman92.igman92.dto.person.PersonDTO;
import org.igman92.igman92.entity.impl.member.Member;
import org.igman92.igman92.entity.impl.member.MemberActivity;
import org.igman92.igman92.entity.impl.member.MemberScoutGroup;
import org.igman92.igman92.entity.impl.member.MemberUnit;
import org.igman92.igman92.services.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/by-scout-group/{scout-group}/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<MemberDTO> getMembersByScoutGroup(@PathVariable("scout-group") Long scoutGroupId) {

        return memberService.getMemberDTOSByScoutGroup(scoutGroupId);
    }

    @RequestMapping(value = "/by-scout-group/{scout-group}/{unit}/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<MemberDTO> getMembersForUnit(@PathVariable("scout-group") Long scoutGroupId, @PathVariable("unit") Long unitId) {

        return memberService.getMembersForUnit(scoutGroupId, unitId);
    }

    @RequestMapping(value = "/without-group/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Member> getAllMembersWithoutScoutGroup() {

        List<Member> members = memberService.getAllMembersWithoutScoutGroup();
        return members;
    }

    @RequestMapping(value = "/add-person/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Member saveMember(@RequestBody PersonDTO personDTO) {

        return memberService.saveMember(personDTO);
    }

    @RequestMapping(value = "/enroll-new-member/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public MemberScoutGroup enrollNewMember(@RequestBody MemberScoutGroup memberScoutGroup) {

        return memberService.enrollNewMember(memberScoutGroup);
    }

    @RequestMapping(value = "/{member-id}/")
    @ResponseStatus(HttpStatus.OK)
    public MemberDTO getMemberDTOByMemberId(@PathVariable("member-id") Long memberId) {

        return memberService.getMemberDTOByMemberId(memberId);
    }

    @RequestMapping(value = "/leaders/{scout-group}/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Member> getLeadersForScoutGroup(@PathVariable("scout-group") Long scoutGroupId) {

        return memberService.getLeadersForScoutGroup(scoutGroupId);
    }

    @RequestMapping(value = "/add-member-to-unit/")
    @ResponseStatus(HttpStatus.OK)
    public void addMemberToUnit(@RequestBody MemberUnit memberUnit) {

        memberService.addMemberToUnit(memberUnit);
    }

    @RequestMapping(value = "/add-member-to-activity/")
    @ResponseStatus(HttpStatus.OK)
    public void addMemberToActivity(@RequestBody MemberActivity memberActivity) {

        memberService.addMemberToActivity(memberActivity);
    }
}
