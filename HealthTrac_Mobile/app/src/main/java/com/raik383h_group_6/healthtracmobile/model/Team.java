package com.raik383h_group_6.healthtracmobile.model;

import android.opengl.Visibility;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Team implements Parcelable {
    private Date dateCreated, dateModified;
    @SerializedName("ID") private long id;
    private String name, description;

    public Team(String name, String description, Date dateCreated, Date dateModified) {
        this.name = name;
        this.description = description;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(dateCreated != null ? dateCreated.getTime() : -1);
        dest.writeLong(dateModified != null ? dateModified.getTime() : -1);
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
    }

    public Team() {
    }

    private Team(Parcel in) {
        long tmpDateCreated = in.readLong();
        this.dateCreated = tmpDateCreated == -1 ? null : new Date(tmpDateCreated);
        long tmpDateModified = in.readLong();
        this.dateModified = tmpDateModified == -1 ? null : new Date(tmpDateModified);
        this.id = in.readLong();
        this.name = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<Team> CREATOR = new Parcelable.Creator<Team>() {
        public Team createFromParcel(Parcel source) {
            return new Team(source);
        }

        public Team[] newArray(int size) {
            return new Team[size];
        }
    };
}
