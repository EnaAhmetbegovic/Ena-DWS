package org.igman92.igman92.services.member;

import org.igman92.igman92.dao.IBaseDao;
import org.igman92.igman92.dto.member.MemberDTO;
import org.igman92.igman92.dto.person.GetPersonDTO;
import org.igman92.igman92.dto.person.PersonDTO;
import org.igman92.igman92.entity.impl.member.*;
import org.igman92.igman92.dao.exceptions.*;
import org.igman92.igman92.entity.impl.organization.Activity;
import org.igman92.igman92.entity.impl.organization.ScoutGroup;
import org.igman92.igman92.entity.impl.organization.Unit;
import org.igman92.igman92.entity.impl.person.Person;
import org.igman92.igman92.repositories.member.*;
import org.igman92.igman92.repositories.scoutGroup.ScoutGroupRepository;
import org.igman92.igman92.services.person.PersonContactService;
import org.igman92.igman92.services.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonContactService personContactService;

    @Autowired
    private MemberUnitRepository memberUnitRepository;

    @Autowired
    private MemberCategoryRepository memberCategoryRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberMeritBadgeRepository memberMeritBadgeRepository;

    @Autowired
    private MemberActivityRepository memberActivityRepository;

    @Autowired
    private MemberRecognitionRepository memberRecognitionRepository;

    @Autowired
    private MemberScoutGroupRepository memberScoutGroupRepository;

    @Autowired
    private MemberWoodbadgeRepository memberWoodbadgeRepository;

    @Autowired
    private ScoutGroupRepository scoutGroupRepository;

    private IBaseDao<Member> memberDao;
    private IBaseDao<Person> personDao;
    private IBaseDao<MemberUnit> memberUnitDao;
    private IBaseDao<MemberActivity> memberActivityDao;
    private IBaseDao<MemberCategory> memberCategoryDao;
    private IBaseDao<MemberScoutGroup> memberScoutGroupDao;
    private IBaseDao<ScoutGroup> scoutGroupDao;
    private IBaseDao<Unit> unitDao;
    private IBaseDao<Activity> activityDao;

    @Autowired
    public void setMemberDao(IBaseDao<Member> daoToSet) {
        memberDao = daoToSet;
        memberDao.setClazz(Member.class);
    }

    @Autowired
    public void setPersonDao(IBaseDao<Person> daoToSet) {
        personDao = daoToSet;
        personDao.setClazz(Person.class);
    }

    @Autowired
    public void setMemberUnitDao(IBaseDao<MemberUnit> daoToSet) {
        memberUnitDao = daoToSet;
        memberUnitDao.setClazz(MemberUnit.class);
    }

    @Autowired
    public void setMemberActivityDao(IBaseDao<MemberActivity> daoToSet) {
        memberActivityDao = daoToSet;
        memberActivityDao.setClazz(MemberActivity.class);
    }

    @Autowired
    public void setCategoryDao(IBaseDao<MemberCategory> daoToSet) {
        memberCategoryDao = daoToSet;
        memberCategoryDao.setClazz(MemberCategory.class);
    }

    @Autowired
    public void setMemberScoutGroupDao(IBaseDao<MemberScoutGroup> daoToSet) {
        memberScoutGroupDao = daoToSet;
        memberScoutGroupDao.setClazz(MemberScoutGroup.class);
    }

    @Autowired
    public void setScoutGroupDao(IBaseDao<ScoutGroup> daoToSet) {
        scoutGroupDao = daoToSet;
        scoutGroupDao.setClazz(ScoutGroup.class);
    }

    @Autowired
    public void setUnitDao(IBaseDao<Unit> daoToSet) {
        unitDao = daoToSet;
        unitDao.setClazz(Unit.class);
    }

    @Autowired
    public void setActivityDao(IBaseDao<Activity> daoToSet) {
        activityDao = daoToSet;
        activityDao.setClazz(Activity.class);
    }

    @Transactional(readOnly = true)
    public List<Member> getAllMembersWithoutScoutGroup() {

        try {

            String query = "SELECT m FROM Member m " +
                    "LEFT JOIN MemberScoutGroup msg ON m.id = msg.member.id " +
                    "WHERE msg.id IS NULL";

            return memberDao.query(query);

        } catch (Exception e) {
            throw new ProjectException("Could not get members without scout group! " + e);
        }
    }

    @Transactional(readOnly = true)
    public List<Member> getMembersByScoutGroup(Long scoutGroupId) {

        if (scoutGroupId == null) {
            throw new IllegalArgumentException("Scout group id must be specified!");
        }

        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("scoutGroupId", scoutGroupId);

            String query = "SELECT m FROM Member m " +
                    "INNER JOIN MemberScoutGroup msg ON m.id = msg.member.id " +
                    "WHERE msg.scoutGroup.id = :scoutGroupId";

            return memberDao.query(query, params);

        } catch (NoEntityFoundException e) {
            return Collections.emptyList();
        } catch (Exception e) {
            throw new ProjectException("Could not get members by scout group! " + e);
        }
    }

    @Transactional
    public MemberDTO getMemberDTOByMemberId(Long memberId) {

        if (memberId == null) {
            throw new ProjectException("Member id must be defined!");
        }

        try {
            MemberDTO memberDTO = new MemberDTO();
            Member member;

            try {
                member = memberRepository.findById(memberId);
            } catch (NoEntityFoundException e) {
                throw new ProjectException("Member with given Id does not exist!");
            }

            List<MemberUnit> memberUnits = memberUnitRepository.findByMemberId(memberId);
            MemberCategory memberCategory = memberCategoryRepository.findByMemberIdAndActive(memberId, true);
            List<MemberActivity> memberActivities = memberActivityRepository.findByMemberId(memberId);
            List<MemberMeritBadge> memberMeritBadges = memberMeritBadgeRepository.findByMemberId(memberId);
            List<MemberRecognition> memberRecognitions = memberRecognitionRepository.findByMemberId(memberId);
            MemberScoutGroup memberScoutGroup = memberScoutGroupRepository.findByMemberId(memberId);
            List<MemberWoodbadge> memberWoodbadges = memberWoodbadgeRepository.findByMemberId(memberId);

            memberDTO.setPersonDTO(getPersonDTO(member.getPerson()));
            memberDTO.setMemberId(member.getId());
            memberDTO.setRecordNumber(member.getRecordNumber());
            memberDTO.setIdCardNumber(member.getIdCardNumber());
            memberDTO.setMemberSince(memberScoutGroup.getMemberSince());
            if (memberCategory != null) {
                memberDTO.setCategory(memberCategory.getCategory());
            }
            memberDTO.setScoutGroup(memberScoutGroup.getScoutGroup());

            List<Activity> activities = new ArrayList<>();
            for (MemberActivity memberActivity : memberActivities) {
                activities.add(memberActivity.getActivity());
            }
            memberDTO.setActivities(activities);

            List<Unit> units = new ArrayList<>();
            for (MemberUnit memberUnit : memberUnits) {
                units.add(memberUnit.getUnit());
            }
            memberDTO.setUnits(units);

            return memberDTO;

        } catch (Exception e) {
            throw new ProjectException("Could not get member with given id! " + e);
        }
    }

    @Transactional
    public Member saveMember(PersonDTO personDTO) {

        try {
            Member newMember = new Member();
            Person newPerson = personService.createPerson(personDTO);

            newMember.setPerson(newPerson);

            memberDao.save(newMember);
            personService.setContacts(newPerson, personDTO);

            return newMember;

        } catch (Exception e) {
            throw new ProjectException("Could not save new member! " + e);
        }
    }

    @Transactional
    public MemberScoutGroup enrollNewMember(MemberScoutGroup memberScoutGroup) {

        if (memberScoutGroup == null || memberScoutGroup.getMember() == null || memberScoutGroup.getScoutGroup() == null
                || memberScoutGroup.getMember().getIdCardNumber() == null) {
            throw new ProjectException("MemberScoutGroup and its attributes must be defined!");
        }

        try {

            MemberScoutGroup newMemberScoutGroup = new MemberScoutGroup();
            Member member = memberRepository.findById(memberScoutGroup.getMember().getId());

            member.setIdCardNumber(memberScoutGroup.getMember().getIdCardNumber());
            member.setRecordNumber(memberScoutGroup.getMember().getRecordNumber());

            memberDao.save(member);

            ScoutGroup scoutGroup = scoutGroupRepository.findByScoutGroup(memberScoutGroup.getScoutGroup().getScoutGroup());

            newMemberScoutGroup.setMember(member);
            newMemberScoutGroup.setMemberSince(memberScoutGroup.getMemberSince());
            newMemberScoutGroup.setScoutGroup(scoutGroup);

            memberScoutGroupDao.save(newMemberScoutGroup);

            return newMemberScoutGroup;

        } catch (Exception e) {
            throw new ProjectException("Could not save new member! " + e);
        }
    }

    @Transactional
    public GetPersonDTO getPersonDTO(Person person) {
        if (person == null || person.getId() == null) {
            throw new ProjectException("Person and its id must be defined!");
        }

        try {
            GetPersonDTO PDTO = new GetPersonDTO();
            PDTO.setPersonId(person.getId());
            PDTO.setFirstName(person.getFirstName());
            PDTO.setLastName(person.getLastName());
            PDTO.setGender(person.getGender());
            PDTO.setJmbg(person.getJmbg());
            PDTO.setFathersFullName(person.getFather().getFirstName() + " " + person.getFather().getLastName());
            PDTO.setMothersFullName(person.getMother().getFirstName() + " " + person.getMother().getLastName());
            PDTO.setDateOfBirth(person.getDateOfBirth());
            PDTO.setPlaceOfBirth(person.getPlaceOfBirth());
            PDTO.setNationality(person.getNationality());
            PDTO.setResidence(person.getResidence());
            PDTO.setBloodType(person.getBloodType());

            return PDTO;

        } catch (Exception e) {
            throw new ProjectException("Coulg not get GetPersonDTO! " + e);
        }
    }

    @Transactional(readOnly = true)
    public List<MemberDTO> getMemberDTOSByScoutGroup(Long scoutGroupId) {

        if (scoutGroupId == null) {
            throw new IllegalArgumentException("Scout group id must be specified!");
        }

        try {
            List<Member> members;
            List<MemberDTO> memberDTOS = new ArrayList<>();

            members = getMembersByScoutGroup(scoutGroupId);

            for (Member member : members) {

                MemberCategory memberCategory = memberCategoryRepository.findByMemberIdAndActive(member.getId(), true);

                MemberDTO memberDTO = new MemberDTO();
                memberDTO.setMemberId(member.getId());
                memberDTO.setPersonDTO(getPersonDTO(member.getPerson()));
                memberDTO.setIdCardNumber(member.getIdCardNumber());
                memberDTO.setRecordNumber(member.getRecordNumber());
                if (memberCategory != null) {
                    memberDTO.setCategory(memberCategory.getCategory());
                } else {
                    memberDTO.setCategory(null);
                }

                memberDTOS.add(memberDTO);
            }

            return memberDTOS;

        } catch (Exception e) {
            throw new ProjectException("Could not get members by scout group! " + e);
        }
    }

    @Transactional(readOnly = true)
    public List<MemberDTO> getMembersForUnit(Long scoutGroupId, Long unitId) {

        if (scoutGroupId == null || unitId == null) {
            throw new IllegalArgumentException("Scout group id and unit id must be specified!");
        }

        try {
            List<Member> members;
            List<MemberDTO> memberDTOS = new ArrayList<>();

            HashMap<String, Object> params = new HashMap<>();
            params.put("scoutGroupId", scoutGroupId);
            params.put("unitId", unitId);

            String query = "SELECT m FROM Member m " +
                    "INNER JOIN MemberScoutGroup msg ON m.id = msg.member.id " +
                    "LEFT JOIN MemberUnit mu ON m.id = mu.member.id " +
                    "LEFT JOIN Unit u ON mu.unit.id = u.id " +
                    "WHERE msg.scoutGroup.id = :scoutGroupId " +
                    "AND mu.unit.id <> :unitId OR mu.unit.id IS NULL " +
                    "AND m.id NOT IN (" +
                    "SELECT u2.leader.id FROM Unit u2 " +
                    "WHERE u2.id = :unitId)";

            members = memberDao.query(query, params);

            for (Member member : members) {

                MemberCategory memberCategory = memberCategoryRepository.findByMemberIdAndActive(member.getId(), true);

                MemberDTO memberDTO = new MemberDTO();
                memberDTO.setMemberId(member.getId());
                memberDTO.setPersonDTO(getPersonDTO(member.getPerson()));
                memberDTO.setIdCardNumber(member.getIdCardNumber());
                memberDTO.setRecordNumber(member.getRecordNumber());
                if (memberCategory != null) {
                    memberDTO.setCategory(memberCategory.getCategory());
                } else {
                    memberDTO.setCategory(null);
                }

                memberDTOS.add(memberDTO);
            }

            return memberDTOS;

        } catch (Exception e) {
            throw new ProjectException("Could not get members by scout group! " + e);
        }
    }

    @Transactional(readOnly = true)
    public List<Member> getLeadersForScoutGroup(Long scoutGroupId) {
        if (scoutGroupId == null) {
            throw new ProjectException("Scout group id must be defined!");
        }

        try {

            try {
                scoutGroupDao.getById(scoutGroupId);
            } catch (NoEntityFoundException e) {
                throw new ProjectException("Scout group with given id does not exist!");
            }

            HashMap<String, Object> params = new HashMap<>();
            params.put("scoutGroupId", scoutGroupId);

            String query = "SELECT m FROM Member m " +
                    "INNER JOIN MemberScoutGroup msg ON m.id = msg.member.id " +
                    "WHERE msg.scoutGroup.id = :scoutGroupId " +
                    "AND m.leader = 1";

            return memberDao.query(query, params);

        } catch (NoEntityFoundException e) {
            return Collections.emptyList();
        } catch (Exception e) {
            throw new ProjectException("Could not get leaders for given scout group! " + e);
        }
    }

    @Transactional
    public void addMemberToUnit(MemberUnit memberUnit) {
        if (memberUnit == null || memberUnit.getMember() == null || memberUnit.getUnit() == null) {
            throw new ProjectException("MemberUnit, Member and Unit must be defined!");
        }

        try {
            MemberUnit newMemberUnit = new MemberUnit();

            newMemberUnit.setMember(memberDao.getById(memberUnit.getMember().getId()));
            newMemberUnit.setUnit(unitDao.getById(memberUnit.getUnit().getId()));
            newMemberUnit.setJoinedUnit(memberUnit.getJoinedUnit());
            newMemberUnit.setLeftUnit(memberUnit.getLeftUnit());

            memberUnitDao.save(newMemberUnit);

        } catch (Exception e) {
            throw new ProjectException("Could not save memberUnit! " + e);
        }
    }

    @Transactional
    public void addMemberToActivity(MemberActivity memberActivity) {
        if (memberActivity == null || memberActivity.getMember() == null || memberActivity.getActivity() == null) {
            throw new ProjectException("MemberActivity, Member and Activity must be defined!");
        }

        try {
            MemberActivity newMemberActivity = new MemberActivity();

            newMemberActivity.setMember(memberDao.getById(memberActivity.getMember().getId()));
            newMemberActivity.setActivity(activityDao.getById(memberActivity.getActivity().getId()));
            newMemberActivity.setComment(memberActivity.getComment());

            memberActivityDao.save(newMemberActivity);

        } catch (Exception e) {
            throw new ProjectException("Could not save memberUnit! " + e);
        }
    }

}
