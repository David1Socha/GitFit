package com.raik383h_group_6.healthtracmobile.model;

import android.opengl.Visibility;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Team {
    private Date dateCreated, dateModified;
    @SerializedName("ID") private long id;
    private String name, description;

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
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

    @SerializedName("Visibility")
    private Visibility visibility;

    public enum Sex {
        @SerializedName("0")
        PUBLIC,
        @SerializedName("1")
        PRIVATE,
        @SerializedName("2")
        SECRET
    }
}
