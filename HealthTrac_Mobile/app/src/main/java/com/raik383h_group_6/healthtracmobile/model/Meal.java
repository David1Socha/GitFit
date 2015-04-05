package com.raik383h_group_6.healthtracmobile.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Meal implements Parcelable {
    @SerializedName("ID")
    private long id;
    private String userID;
    private int calories;

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    private Date dateCreated;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.userID);
        dest.writeInt(this.calories);
        dest.writeLong(dateCreated != null ? dateCreated.getTime() : -1);
    }

    public Meal() {
    }

    private Meal(Parcel in) {
        this.id = in.readLong();
        this.userID = in.readString();
        this.calories = in.readInt();
        long tmpDateCreated = in.readLong();
        this.dateCreated = tmpDateCreated == -1 ? null : new Date(tmpDateCreated);
    }

    public static final Parcelable.Creator<Meal> CREATOR = new Parcelable.Creator<Meal>() {
        public Meal createFromParcel(Parcel source) {
            return new Meal(source);
        }

        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };
}
