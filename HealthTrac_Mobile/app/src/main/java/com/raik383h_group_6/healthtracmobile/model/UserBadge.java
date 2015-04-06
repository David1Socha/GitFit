package com.raik383h_group_6.healthtracmobile.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class UserBadge implements Parcelable {
    @SerializedName("ID")
    private long id;
    private long badgeID;
    private String userID;

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBadgeID() {
        return badgeID;
    }

    public void setBadgeID(long badgeID) {
        this.badgeID = badgeID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    private Date dateCompleted;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeLong(this.badgeID);
        dest.writeString(this.userID);
        dest.writeLong(dateCompleted != null ? dateCompleted.getTime() : -1);
    }

    public UserBadge() {
    }

    private UserBadge(Parcel in) {
        this.id = in.readLong();
        this.badgeID = in.readLong();
        this.userID = in.readString();
        long tmpDateCompleted = in.readLong();
        this.dateCompleted = tmpDateCompleted == -1 ? null : new Date(tmpDateCompleted);
    }

    public static final Parcelable.Creator<UserBadge> CREATOR = new Parcelable.Creator<UserBadge>() {
        public UserBadge createFromParcel(Parcel source) {
            return new UserBadge(source);
        }

        public UserBadge[] newArray(int size) {
            return new UserBadge[size];
        }
    };
}
