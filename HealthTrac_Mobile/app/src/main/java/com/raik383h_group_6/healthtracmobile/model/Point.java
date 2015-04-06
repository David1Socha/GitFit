package com.raik383h_group_6.healthtracmobile.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Point implements Parcelable {
    @SerializedName("ID")
    private long id;
    private long activityID;
    private double lng;

    public Point() {

    }

    public Point(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getActivityId() {
        return activityID;
    }

    public void setActivityId(long activityId) {
        this.activityID = activityId;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    private double lat;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeLong(this.activityID);
        dest.writeDouble(this.lng);
        dest.writeDouble(this.lat);
    }

    private Point(Parcel in) {
        this.id = in.readLong();
        this.activityID = in.readLong();
        this.lng = in.readDouble();
        this.lat = in.readDouble();
    }

    public static final Parcelable.Creator<Point> CREATOR = new Parcelable.Creator<Point>() {
        public Point createFromParcel(Parcel source) {
            return new Point(source);
        }

        public Point[] newArray(int size) {
            return new Point[size];
        }
    };
}
