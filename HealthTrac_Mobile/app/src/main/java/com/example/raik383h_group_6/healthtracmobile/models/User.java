package com.example.raik383h_group_6.healthtracmobile.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Aaron on 2/19/2015.
 */
public class User implements Parcelable{

    public enum Sex {
        Male, Female
    }


    private String id;
    private String firstName;
    private String lastName;
    private String preferredName;
    private String email;
    private Sex sex;
    private int height;
    private int weight;
    private String birthdate;
    private String username;
    private String dateCreated;

    public User() {

    }

    public User(String id, String firstName, String lastName, String preferredName, String email, Sex sex, int height, int weight, String birthdate, String username, String dateCreated) {
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
        this.dateCreated = dateCreated;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(preferredName);
        dest.writeString(email);
        dest.writeValue(sex);
        dest.writeInt(height);
        dest.writeInt(weight);
        dest.writeValue(birthdate);
        dest.writeString(username);
        dest.writeString(dateCreated);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            User user = new User();
            user.id = source.readString();
            user.firstName = source.readString();
            user.lastName = source.readString();
            user.preferredName = source.readString();
            user.email = source.readString();
            user.sex = Sex.Male;
            user.height = Integer.valueOf(source.readString());
            user.weight = Integer.valueOf(source.readString());
            user.birthdate = source.readString();
            user.username = source.readString();
            user.dateCreated = source.readString();
            return user;
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDateCreated() { return dateCreated; }

    public void setDateCreated(String dateCreated) { this.dateCreated = dateCreated; }
}
