package com.raik383h_group_6.healthtracmobile.service.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raik383h_group_6.healthtracmobile.model.FacebookUser;

import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.http.Header;

import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;

public interface FacebookService {
    Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES)
            .create();
    Converter DATA_CONVERTER = new GsonConverter(GSON);
    String SERVICE_ENDPOINT = "https://graph.facebook.com/v2.2";
    String AUTH_PREFIX = "Bearer ";

    @GET("/me")
    FacebookUser getUser(@Header("Authorization") String token);
}
