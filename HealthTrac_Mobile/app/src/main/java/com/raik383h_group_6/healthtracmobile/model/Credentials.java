package com.raik383h_group_6.healthtracmobile.model;

public class Credentials {
    private String provider;
    private String secret;

    public Credentials() {}
    public Credentials(String token, String secret, String provider) {
        this.token = token;
        this.secret = secret;
        this.provider = provider;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    private String token;
}
