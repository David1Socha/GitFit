package com.raik383h_group_6.healthtracmobile.service;

import com.raik383h_group_6.healthtracmobile.model.Token;

public interface IOAuthServiceAdapter {
    public Token getRequestToken();
    public String getAuthorizationUrl(Token t);
}
