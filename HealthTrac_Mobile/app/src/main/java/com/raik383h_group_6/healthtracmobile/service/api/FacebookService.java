package com.raik383h_group_6.healthtracmobile.service.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raik383h_group_6.healthtracmobile.model.FacebookUser;

import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.http.Header;

import static com.google.gson.FieldNamingPolicy.UPPER_CAMEL_CASE;

public interface FacebookService {
    Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(UPPER_CAMEL_CASE)
            .create();
    Converter DATA_CONVERTER = new GsonConverter(GSON);
    String SERVICE_ENDPOINT = "https://graph.facebook.com/v2.2";

    @GET("me")
    FacebookUser getUser(@Header("Authorization") String token);
}
