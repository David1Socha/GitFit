package com.raik383h_group_6.healthtracmobile.service.oauth;

public interface IAsyncOAuthService {
    String getAccessTokenAsync(String requestToken, String verifier);
    String getAuthorizationUrl();

}
