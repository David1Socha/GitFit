package com.raik383h_group_6.healthtracmobile.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Goal implements Parcelable {
    @SerializedName("ID")
    private long id;
    private String name;
    private Field field;

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

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    private double threshold;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.field == null ? -1 : this.field.ordinal());
        dest.writeDouble(this.threshold);
    }

    public Goal() {
    }

    private Goal(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        int tmpField = in.readInt();
        this.field = tmpField == -1 ? null : Field.values()[tmpField];
        this.threshold = in.readDouble();
    }

    public static final Parcelable.Creator<Goal> CREATOR = new Parcelable.Creator<Goal>() {
        public Goal createFromParcel(Parcel source) {
            return new Goal(source);
        }

        public Goal[] newArray(int size) {
            return new Goal[size];
        }
    };
}
