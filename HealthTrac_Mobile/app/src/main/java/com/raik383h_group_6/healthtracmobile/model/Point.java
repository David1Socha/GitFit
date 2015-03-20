package com.raik383h_group_6.healthtracmobile.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Point implements Parcelable {
    private long id, activityId;
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
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
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
        dest.writeLong(this.activityId);
        dest.writeDouble(this.lng);
        dest.writeDouble(this.lat);
    }

    private Point(Parcel in) {
        this.id = in.readLong();
        this.activityId = in.readLong();
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
