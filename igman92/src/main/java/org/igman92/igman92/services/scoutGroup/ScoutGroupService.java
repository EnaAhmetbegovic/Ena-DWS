package org.igman92.igman92.services.scoutGroup;

import org.igman92.igman92.dao.IBaseDao;
import org.igman92.igman92.dao.exceptions.ProjectException;
import org.igman92.igman92.dto.scoutGroup.YearlyDataDTO;
import org.igman92.igman92.entity.impl.member.Member;
import org.igman92.igman92.entity.impl.organization.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
public class ScoutGroupService {

    private IBaseDao<Member> memberDao;
    private IBaseDao<Activity> activityDao;

    @Autowired
    public void setMemberDao(IBaseDao<Member> daoToSet) {
        memberDao = daoToSet;
        memberDao.setClazz(Member.class);
    }

    @Autowired
    public void setActivityDao(IBaseDao<Activity> daoToSet) {
        activityDao = daoToSet;
        activityDao.setClazz(Activity.class);
    }

    @Transactional(readOnly = true)
    public YearlyDataDTO getBasicYearlyData(Long scoutGroupId) {

        if (scoutGroupId == null) {
            throw new ProjectException("Scout group id must be defined!");
        }

        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("scoutGroupId", scoutGroupId);

            String query1 = "SELECT COUNT(*) FROM Member m " +
                    "INNER JOIN MemberScoutGroup msg ON m.id = msg.member.id " +
                    "WHERE msg.scoutGroup.id = :scoutGroupId " +
                    "AND year(msg.memberSince) = year(current_date())";

            Long numberOfNewMembers = memberDao.count(query1, params);

            String query2 = "SELECT COUNT(*) FROM Member m " +
                    "INNER JOIN MemberScoutGroup msg ON m.id = msg.member.id " +
                    "WHERE msg.scoutGroup.id = :scoutGroupId ";

            Long totalNumberOfMembers = memberDao.count(query2, params);

            String query3 = "SELECT COUNT(*) FROM Activity a " +
                    "WHERE a.organizer.id = :scoutGroupId ";

            Long numberOfYearlyActivities = activityDao.count(query3, params);

            YearlyDataDTO yearlyDataDTO = new YearlyDataDTO();
            yearlyDataDTO.setNumberOfNewMembers(numberOfNewMembers);
            yearlyDataDTO.setNumberOfYearlyActivities(numberOfYearlyActivities);
            yearlyDataDTO.setTotalNumberOfMembers(totalNumberOfMembers);

            return yearlyDataDTO;

        } catch (Exception e) {
            throw new ProjectException("Could not get yearly data! " + e);
        }
    }
}
