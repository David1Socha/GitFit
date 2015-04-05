package com.raik383h_group_6.healthtracmobile.service.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raik383h_group_6.healthtracmobile.model.Activity;
import com.raik383h_group_6.healthtracmobile.model.Badge;

import java.util.List;

import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;
import retrofit.http.Query;

import static com.google.gson.FieldNamingPolicy.UPPER_CAMEL_CASE;

public interface BadgeService {
    Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(UPPER_CAMEL_CASE).setDateFormat(ApiConstants.ASPNET_DATE_FORMAT)
            .create();
    Converter DATA_CONVERTER = new GsonConverter(GSON);

    String SERVICE_ENDPOINT = "https://se6.azurewebsites.net";

    @GET("/api/Badges/{id}")
    Badge getBadge(@Path("id") long id, @Header("Authorization") String token);

    @GET("/api/Badges")
    List<Badge> getBadges(@Header("Authorization") String token);

}
