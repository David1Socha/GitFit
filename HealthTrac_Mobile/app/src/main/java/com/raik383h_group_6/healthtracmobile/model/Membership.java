package com.raik383h_group_6.healthtracmobile.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Membership {
    @SerializedName("ID") private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTeamID() {
        return teamID;
    }

    public void setTeamID(long teamID) {
        this.teamID = teamID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public MembershipStatus getMembershipStatus() {
        return membershipStatus;
    }

    public void setMembershipStatus(MembershipStatus membershipStatus) {
        this.membershipStatus = membershipStatus;
    }

    private long teamID;
    private String userID;
    private Date dateCreated, dateModified;
    @SerializedName("MembershipStatus") private MembershipStatus membershipStatus; //TODO attribute may be unnecessary

    public enum MembershipStatus {
        @SerializedName("0")
        BANNED,
        @SerializedName("1")
        WAITING_USER,
        @SerializedName("2")
        WAITING_TEAM,
        @SerializedName("3")
        MEMBER,
        @SerializedName("4")
        INACTIVE,
        @SerializedName("5")
        ADMIN
    }
}
