package com.raik383h_group_6.healthtracmobile.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Badge implements Parcelable {
    @SerializedName("ID")
    private long id;
    private String name;
    private double threshold;

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
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

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    private Field field;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeDouble(this.threshold);
        dest.writeInt(this.field == null ? -1 : this.field.ordinal());
    }

    public Badge() {
    }

    private Badge(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.threshold = in.readDouble();
        int tmpField = in.readInt();
        this.field = tmpField == -1 ? null : Field.values()[tmpField];
    }

    public static final Parcelable.Creator<Badge> CREATOR = new Parcelable.Creator<Badge>() {
        public Badge createFromParcel(Parcel source) {
            return new Badge(source);
        }

        public Badge[] newArray(int size) {
            return new Badge[size];
        }
    };
}
