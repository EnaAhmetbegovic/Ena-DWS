package org.igman92.igman92.services.activity;

import org.igman92.igman92.dao.IBaseDao;
import org.igman92.igman92.dao.exceptions.NoEntityFoundException;
import org.igman92.igman92.dao.exceptions.ProjectException;
import org.igman92.igman92.dto.activity.ActivityDTO;
import org.igman92.igman92.entity.impl.member.MemberActivity;
import org.igman92.igman92.entity.impl.organization.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class ActivityService {

    private IBaseDao<Activity> activityDao;
    private IBaseDao<MemberActivity> memberActivityDao;

    @Autowired
    public void setActivityDao(IBaseDao<Activity> daoToSet) {
        activityDao = daoToSet;
        activityDao.setClazz(Activity.class);
    }

    @Autowired
    public void setMemberActivityDao(IBaseDao<MemberActivity> daoToSet) {
        memberActivityDao = daoToSet;
        memberActivityDao.setClazz(MemberActivity.class);
    }

    @Transactional(readOnly = true)
    public List<Activity> getActivitiesByScoutGroup(Long scoutGroupId) {

        if (scoutGroupId == null) {
            throw new IllegalArgumentException("Scout group id must be specified!");
        }

        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("scoutGroupId", scoutGroupId);

            String query = "SELECT a FROM Activity a " +
                    "WHERE a.organizer.id = :scoutGroupId";

            return activityDao.query(query, params);

        } catch (NoEntityFoundException e) {
            return Collections.emptyList();
        } catch (Exception e) {
            throw new ProjectException("Could not get members by scout group! " + e);
        }
    }

    @Transactional
    public Activity addNewActivity(Activity activity) {

        if (activity == null) {
            throw new ProjectException("Activity parameters must be specified!");
        }

        try {
            Activity newActivity = new Activity();

            newActivity.setActivityType(activity.getActivityType());
            newActivity.setChief(activity.getChief());
            newActivity.setHead(activity.getHead());
            newActivity.setName(activity.getName());
            newActivity.setOrganizer(activity.getOrganizer());
            newActivity.setPlace(activity.getPlace());
            newActivity.setStartDate(activity.getStartDate());
            newActivity.setEndDate(activity.getEndDate());

            activityDao.save(newActivity);

            return newActivity;

        } catch (Exception e) {
            throw new ProjectException("Could not save new activity! " + e);
        }
    }

    @Transactional(readOnly = true)
    public ActivityDTO getMemberActivities(Long activityId) {

        if (activityId == null) {
            throw new ProjectException("Activity id must be specified!");
        }

        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("activityId", activityId);

            String query = "SELECT ma FROM Activity a " +
                    "INNER JOIN MemberActivity ma on a.id = ma.activity.id " +
                    "WHERE a.id = :activityId";

            List<MemberActivity> memberActivities;

            try {
                memberActivities = memberActivityDao.query(query, params);
            } catch (NoEntityFoundException e) {
                memberActivities = Collections.emptyList();
            }

            ActivityDTO activityDTO = new ActivityDTO();
            activityDTO.setActivity(activityDao.getById(activityId));
            activityDTO.setMemberActivities(memberActivities);

            return activityDTO;

        } catch (Exception e) {
            throw new ProjectException("Could not get memberDTO! " + e);
        }
    }
}
