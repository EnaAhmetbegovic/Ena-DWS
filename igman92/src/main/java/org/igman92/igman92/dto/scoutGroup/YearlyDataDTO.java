package org.igman92.igman92.dto.scoutGroup;

public class YearlyDataDTO {

    private Long totalNumberOfMembers;
    private Long numberOfNewMembers;
    private Long numberOfYearlyActivities;

    public YearlyDataDTO() {}

    public Long getTotalNumberOfMembers() {
        return totalNumberOfMembers;
    }

    public void setTotalNumberOfMembers(Long totalNumberOfMembers) {
        this.totalNumberOfMembers = totalNumberOfMembers;
    }

    public Long getNumberOfNewMembers() {
        return numberOfNewMembers;
    }

    public void setNumberOfNewMembers(Long numberOfNewMembers) {
        this.numberOfNewMembers = numberOfNewMembers;
    }

    public Long getNumberOfYearlyActivities() {
        return numberOfYearlyActivities;
    }

    public void setNumberOfYearlyActivities(Long numberOfYearlyActivities) {
        this.numberOfYearlyActivities = numberOfYearlyActivities;
    }
}
