package com.raik383h_group_6.healthtracmobile.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class User implements Parcelable {
    private Date dateCreated, dateModified, birthDate;
    private String firstName, lastName, preferredName, email, userName, location;
    private String id;
    private double height, width;

    public User(Date birthDate, Date dateCreated, Date dateModified, String email, String firstName, double height, String lastName, String location, String preferredName, User.Sex sex, String userName, double weight) {
        this.birthDate = birthDate;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.email = email;
        this.firstName = firstName;
        this.height = height;
        this.lastName = lastName;
        this.location = location;
        this.preferredName = preferredName;
        this.sex = sex;
        this.userName = userName;
        this.width = weight; // :( TODO fix me to weight when possible
    }

    public String getLocation() {return  location;}

    public void setLocation(String location) {
        this.location = location;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @SerializedName("Sex")
    private Sex sex;

    public enum Sex {
        @SerializedName("0")
        MALE,
        @SerializedName("1")
        FEMALE
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(dateCreated != null ? dateCreated.getTime() : -1);
        dest.writeLong(dateModified != null ? dateModified.getTime() : -1);
        dest.writeLong(birthDate != null ? birthDate.getTime() : -1);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.preferredName);
        dest.writeString(this.email);
        dest.writeString(this.userName);
        dest.writeString(this.id);
        dest.writeDouble(this.height);
        dest.writeDouble(this.width);
        dest.writeInt(this.sex == null ? -1 : this.sex.ordinal());
        dest.writeString(this.location);
    }

    public User() {
    }

    private User(Parcel in) {
        long tmpDateCreated = in.readLong();
        this.dateCreated = tmpDateCreated == -1 ? null : new Date(tmpDateCreated);
        long tmpDateModified = in.readLong();
        this.dateModified = tmpDateModified == -1 ? null : new Date(tmpDateModified);
        long tmpBirthDate = in.readLong();
        this.birthDate = tmpBirthDate == -1 ? null : new Date(tmpBirthDate);
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.preferredName = in.readString();
        this.email = in.readString();
        this.userName = in.readString();
        this.id = in.readString();
        this.height = in.readDouble();
        this.width = in.readDouble();
        int tmpSex = in.readInt();
        this.sex = tmpSex == -1 ? null : Sex.values()[tmpSex];
        this.location = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
