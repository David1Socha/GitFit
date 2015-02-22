package com.raik383h_group_6.healthtracmobile.access;

import com.google.gson.Gson;
import com.raik383h_group_6.healthtracmobile.model.AccessGrant;
import com.raik383h_group_6.healthtracmobile.model.Credentials;
import com.raik383h_group_6.healthtracmobile.model.UserLogin;

import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;

public interface IAccountService {
    Converter DATA_CONVERTER = new GsonConverter(new Gson());
    String SERVICE_ENDPOINT = "https://se6.azurewebsites.net";

    @POST("/api/Account/Login")
    AccessGrant logIn(@Body Credentials request);

    @POST("/api/Account/Register")
    void register(@Body UserLogin request);
}
