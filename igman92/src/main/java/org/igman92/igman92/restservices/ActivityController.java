package org.igman92.igman92.restservices;

import org.igman92.igman92.dto.activity.ActivityDTO;
import org.igman92.igman92.entity.impl.member.MemberActivity;
import org.igman92.igman92.entity.impl.organization.Activity;
import org.igman92.igman92.services.activity.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/by-scout-group/{scout-group}/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Activity> getActivitiesByScoutGroup(@PathVariable("scout-group") Long scoutGroupId) {

        return activityService.getActivitiesByScoutGroup(scoutGroupId);
    }

    @RequestMapping(value = "/add/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Activity addNewActivity(@RequestBody Activity activity) {

        return activityService.addNewActivity(activity);
    }

    @RequestMapping(value = "/{activity}/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ActivityDTO getMemberActivities(@PathVariable("activity") Long activityId) {

        return activityService.getMemberActivities(activityId);
    }
}
