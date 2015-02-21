package com.raik383h_group_6.healthtracmobile.model;

public class Token {
    private String token;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String secret;
    public Token() {}
    public Token(String token) {
        this.token = token;
    }
    public Token(String token, String secret) {
        this.token = token;
        this.secret = secret;
    }
}
