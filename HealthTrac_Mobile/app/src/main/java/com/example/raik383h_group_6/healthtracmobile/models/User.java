package com.example.raik383h_group_6.healthtracmobile.models;

import java.util.Date;

/**
 * Created by Aaron on 2/19/2015.
 */
public class User {
    public enum Sex {
        Male, Female
    }

    private long id;
    private String firstName;
    private String lastName;
    private String preferredName;
    private String email;
    private Sex sex;
    private int height;
    private int weight;
    private Date birthdate;
    private String username;

    public User(long id, String firstName, String lastName, String preferredName, String email, Sex sex, int height, int weight, Date birthdate, String username) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.preferredName = preferredName;
        this.email = email;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.birthdate = birthdate;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
