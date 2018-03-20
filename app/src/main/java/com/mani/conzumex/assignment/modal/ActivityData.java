package com.mani.conzumex.assignment.modal;

public class ActivityData {
    int ActivityID;
    String ActivityName, ActivityIntensity, ActivityStartTimeDate, ActivityEndTimeDate;

    public int getActivityID() {
        return ActivityID;
    }

    public void setActivityID(int activityID) {
        ActivityID = activityID;
    }

    public String getActivityName() {
        return ActivityName;
    }

    public void setActivityName(String activityName) {
        ActivityName = activityName;
    }

    public String getActivityIntensity() {
        return ActivityIntensity;
    }

    public void setActivityIntensity(String activityIntensity) {
        ActivityIntensity = activityIntensity;
    }

    public String getActivityStartTimeDate() {
        return ActivityStartTimeDate;
    }

    public void setActivityStartTimeDate(String activityStartTimeDate) {
        ActivityStartTimeDate = activityStartTimeDate;
    }

    public String getActivityEndTimeDate() {
        return ActivityEndTimeDate;
    }

    public void setActivityEndTimeDate(String activityEndTimeDate) {
        ActivityEndTimeDate = activityEndTimeDate;
    }

    @Override
    public String toString() {
        return "ActivityData {id-" + ActivityID + ", taskDetails-" + ActivityName + ", propity-" + ActivityIntensity + ", status-" + ActivityStartTimeDate + ", notes-" + ActivityEndTimeDate + "}";
    }

}
