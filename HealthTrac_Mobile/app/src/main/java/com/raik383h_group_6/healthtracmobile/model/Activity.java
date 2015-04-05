package com.raik383h_group_6.healthtracmobile.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Activity implements Parcelable {
    private double duration, distance;
    private long steps, id;
    private Date startDate;

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public long getSteps() {
        return steps;
    }

    public void setSteps(long steps) {
        this.steps = steps;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    private String userId;
    private ActivityType type;
    public enum ActivityType {
        WALKING, JOGGING, RUNNING, BIKING
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.duration);
        dest.writeDouble(this.distance);
        dest.writeLong(this.steps);
        dest.writeLong(this.id);
        dest.writeLong(startDate != null ? startDate.getTime() : -1);
        dest.writeString(this.userId);
        dest.writeInt(this.type == null ? -1 : this.type.ordinal());
    }

    public Activity() {
    }

    private Activity(Parcel in) {
        this.duration = in.readDouble();
        this.distance = in.readDouble();
        this.steps = in.readLong();
        this.id = in.readLong();
        long tmpStartDate = in.readLong();
        this.startDate = tmpStartDate == -1 ? null : new Date(tmpStartDate);
        this.userId = in.readString();
        int tmpType = in.readInt();
        this.type = tmpType == -1 ? null : ActivityType.values()[tmpType];
    }

    public static final Parcelable.Creator<Activity> CREATOR = new Parcelable.Creator<Activity>() {
        public Activity createFromParcel(Parcel source) {
            return new Activity(source);
        }

        public Activity[] newArray(int size) {
            return new Activity[size];
        }
    };
}
