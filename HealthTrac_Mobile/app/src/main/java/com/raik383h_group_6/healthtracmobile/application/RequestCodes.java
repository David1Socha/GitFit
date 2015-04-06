package com.raik383h_group_6.healthtracmobile.application;

public final class RequestCodes {
    private RequestCodes() {
        //unused
    }
    public static final int AUTH = 0, CREATE_ACCOUNT = 1,
            OAUTH_TO_SIGN_IN = 2,
            OAUTH_TO_CREATE_ACCOUNT = 3, FB_LOGIN_REQ = 4,
            TW_LOGIN_REQ = 5, UPDATE_TEAM = 6, UPDATE_USER = 7;
}
