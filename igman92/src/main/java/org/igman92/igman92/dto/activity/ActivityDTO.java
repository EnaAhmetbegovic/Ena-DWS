package org.igman92.igman92.dto.activity;

import org.igman92.igman92.entity.impl.member.MemberActivity;
import org.igman92.igman92.entity.impl.organization.Activity;

import java.util.List;

public class ActivityDTO {

    private Activity activity;
    private List<MemberActivity> memberActivities;

    public ActivityDTO() {}

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public List<MemberActivity> getMemberActivities() {
        return memberActivities;
    }

    public void setMemberActivities(List<MemberActivity> memberActivities) {
        this.memberActivities = memberActivities;
    }
}
