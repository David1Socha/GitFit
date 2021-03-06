package com.raik383h_group_6.healthtracmobile.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class AccessGrant implements Parcelable {
    @SerializedName("userName") private String userName;
    @SerializedName("access_token") private String accessToken;
    @SerializedName("token_type") private String tokenType;
    @SerializedName("expires_in") private String expiresIn;
    @SerializedName(".issued") private Date issued;

    public String getAuthHeader() {return tokenType + " " + accessToken;}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Date getIssued() {
        return issued;
    }

    public void setIssued(Date issued) {
        this.issued = issued;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullToken() {
        return tokenType + " " + accessToken;
    }

    @SerializedName(".expires") private Date expires;
    @SerializedName("id") private String id;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.accessToken);
        dest.writeString(this.tokenType);
        dest.writeString(this.expiresIn);
        dest.writeLong(this.issued != null ? this.issued.getTime() : -1);
        dest.writeLong(this.expires != null ? this.expires.getTime() : -1);
        dest.writeString(this.id);
    }

    public AccessGrant() {
    }

    public boolean isExpired() {
        Date now = new Date();
        boolean expired = expires != null && expires.before(now);
        return expired;
    }

    private AccessGrant(Parcel in) {
        this.userName = in.readString();
        this.accessToken = in.readString();
        this.tokenType = in.readString();
        this.expiresIn = in.readString();
        long tmpIssued = in.readLong();
        this.issued = tmpIssued == -1 ? null : new Date(tmpIssued);
        long tmpExpires = in.readLong();
        this.expires = tmpExpires == -1 ? null : new Date(tmpExpires);
        this.id = in.readString();
    }

    public static final Parcelable.Creator<AccessGrant> CREATOR = new Parcelable.Creator<AccessGrant>() {
        public AccessGrant createFromParcel(Parcel source) {
            return new AccessGrant(source);
        }

        public AccessGrant[] newArray(int size) {
            return new AccessGrant[size];
        }
    };
}
