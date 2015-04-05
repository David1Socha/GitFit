package com.raik383h_group_6.healthtracmobile.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class UserGoal implements Parcelable {
    @SerializedName("ID")
    private long id;

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

    public long getBadgeID() {
        return badgeID;
    }

    public void setBadgeID(long badgeID) {
        this.badgeID = badgeID;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    private String userID;
    private long badgeID;
    private Date dateCompleted;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.userID);
        dest.writeLong(this.badgeID);
        dest.writeLong(dateCompleted != null ? dateCompleted.getTime() : -1);
    }

    public UserGoal() {
    }

    private UserGoal(Parcel in) {
        this.id = in.readLong();
        this.userID = in.readString();
        this.badgeID = in.readLong();
        long tmpDateCompleted = in.readLong();
        this.dateCompleted = tmpDateCompleted == -1 ? null : new Date(tmpDateCompleted);
    }

    public static final Parcelable.Creator<UserGoal> CREATOR = new Parcelable.Creator<UserGoal>() {
        public UserGoal createFromParcel(Parcel source) {
            return new UserGoal(source);
        }

        public UserGoal[] newArray(int size) {
            return new UserGoal[size];
        }
    };
}
