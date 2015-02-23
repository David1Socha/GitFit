package com.raik383h_group_6.healthtracmobile.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Membership {
    @SerializedName("ID") private long id;
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
