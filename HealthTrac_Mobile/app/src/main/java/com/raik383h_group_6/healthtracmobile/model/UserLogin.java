package com.raik383h_group_6.healthtracmobile.model;

public class UserLogin {

    public UserLogin() {}

    public UserLogin(User user, Credentials credentials) {
        this.user = user;
        this.credentials = credentials;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private Credentials credentials;
    private User user;
}
