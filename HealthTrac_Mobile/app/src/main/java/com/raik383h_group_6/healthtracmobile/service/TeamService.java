package com.raik383h_group_6.healthtracmobile.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raik383h_group_6.healthtracmobile.model.User;

import java.util.List;

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

public interface TeamService {
    Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(UPPER_CAMEL_CASE)
            .create();
    Converter DATA_CONVERTER = new GsonConverter(GSON);

    String SERVICE_ENDPOINT = "https://se6.azurewebsites.net";

    @GET("/api/Teams/{id}")
    User getTeam(@Path("id") long id, @Header("Authorization") String token);

    @GET("api/Teams")
    List<User> getTeams(@Header("Authorization") String token);

    @GET("api/Teams")
    List<User> getTeams(@Query("userId") String userId, @Header("Authorization") String token);

    @PUT("api/Teams/{id}")
    void updateUser(@Path("id") long id, @Body Team team, @Header("Authorization") String token);

    @POST("api/Teams")
    Team createTeam(@Body Team team, @Header("Authorization") String token);
}
