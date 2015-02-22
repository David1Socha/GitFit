package com.raik383h_group_6.healthtracmobile.model;

import com.google.gson.annotations.SerializedName;

public class AccessGrant {
    private String userName;
    @SerializedName("access_token") private String accessToken;
    @SerializedName("token_type") private String tokenType;
    @SerializedName("expires_in") private String expiresIn;
    @SerializedName(".issued") private String issued;
    @SerializedName(".expires") private String expires;
    @SerializedName("id") private String id;
}
