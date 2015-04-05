package com.raik383h_group_6.healthtracmobile.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class EnergyLevel implements Parcelable {
    @SerializedName("ID")
    private long id;
    private String userID;

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
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

    private Mood mood;

    public enum Mood {
        @SerializedName("0")
        EXCITED,
        @SerializedName("1")
        TIRED,
        @SerializedName("2")
        PROUD
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.userID);
        dest.writeInt(this.mood == null ? -1 : this.mood.ordinal());
    }

    public EnergyLevel() {
    }

    private EnergyLevel(Parcel in) {
        this.id = in.readLong();
        this.userID = in.readString();
        int tmpMood = in.readInt();
        this.mood = tmpMood == -1 ? null : Mood.values()[tmpMood];
    }

    public static final Parcelable.Creator<EnergyLevel> CREATOR = new Parcelable.Creator<EnergyLevel>() {
        public EnergyLevel createFromParcel(Parcel source) {
            return new EnergyLevel(source);
        }

        public EnergyLevel[] newArray(int size) {
            return new EnergyLevel[size];
        }
    };
}
