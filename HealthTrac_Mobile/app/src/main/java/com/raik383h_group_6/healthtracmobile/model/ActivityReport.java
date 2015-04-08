package com.raik383h_group_6.healthtracmobile.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ActivityReport implements Parcelable {
    private Date date;
    @SerializedName("ID")
    private long id;
    private String userID;
    private long steps;
    private double distance;

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public long getSteps() {
        return steps;
    }

    public void setSteps(long steps) {
        this.steps = steps;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    private double duration;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(date != null ? date.getTime() : -1);
        dest.writeLong(this.id);
        dest.writeString(this.userID);
        dest.writeLong(this.steps);
        dest.writeDouble(this.distance);
        dest.writeDouble(this.duration);
    }

    public ActivityReport() {
    }

    private ActivityReport(Parcel in) {
        long tmpDate = in.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
        this.id = in.readLong();
        this.userID = in.readString();
        this.steps = in.readLong();
        this.distance = in.readDouble();
        this.duration = in.readDouble();
    }

    public static final Parcelable.Creator<ActivityReport> CREATOR = new Parcelable.Creator<ActivityReport>() {
        public ActivityReport createFromParcel(Parcel source) {
            return new ActivityReport(source);
        }

        public ActivityReport[] newArray(int size) {
            return new ActivityReport[size];
        }
    };
}
