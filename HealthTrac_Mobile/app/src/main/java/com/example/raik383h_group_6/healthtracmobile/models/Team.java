package com.example.raik383h_group_6.healthtracmobile.models;

/**
 * Created by Aaron on 2/19/2015.
 */
public class Team {
    public enum Visibility {
        PUBLIC, PRIVATE, SECRET
    }

    private long id;
    private String name;
    private String description;
    private Visibility visibility;

    public Team(long id, String name, String description, Visibility visibility) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.visibility = visibility;
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

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }
}
