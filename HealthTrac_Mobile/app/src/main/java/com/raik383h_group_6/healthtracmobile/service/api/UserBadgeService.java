package com.raik383h_group_6.healthtracmobile.service.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raik383h_group_6.healthtracmobile.model.Membership;
import com.raik383h_group_6.healthtracmobile.model.UserBadge;

import java.util.List;

import retrofit.client.Response;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

import static com.google.gson.FieldNamingPolicy.UPPER_CAMEL_CASE;

public interface UserBadgeService {
    Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(UPPER_CAMEL_CASE).setDateFormat(ApiConstants.ASPNET_DATE_FORMAT)
            .create();
    Converter DATA_CONVERTER = new GsonConverter(GSON);

    String SERVICE_ENDPOINT = "https://se6.azurewebsites.net";

    @GET("/api/UserBadges/{id}")
    UserBadge getUserBadge(@Path("id") long id, @Header("Authorization") String token);

    @GET("/api/UserBadges")
    UserBadge getUserBadge(@Query("userId") String userId, @Query("badgeId") long badgeId, @Header("Authorization") String token);

    @GET("/api/UserBadges")
    List<UserBadge> getUserBadges(@Header("Authorization") String token);

    @GET("/api/UserBadges")
    List<UserBadge> getUserBadges(@Query("userId") String userId, @Header("Authorization") String token);

    @GET("/api/UserBadges")
    List<UserBadge> getUserBadges(@Query("badgeId") long badgeId, @Header("Authorization") String token);

    @POST("/api/UserBadges")
    UserBadge createUserBadge(@Body UserBadge userBadge, @Header("Authorization") String token);

}
