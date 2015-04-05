package com.raik383h_group_6.healthtracmobile.service.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raik383h_group_6.healthtracmobile.model.UserGoal;

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

public interface UserGoalService {
    Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(UPPER_CAMEL_CASE).setDateFormat(ApiConstants.ASPNET_DATE_FORMAT)
            .create();
    Converter DATA_CONVERTER = new GsonConverter(GSON);

    String SERVICE_ENDPOINT = "https://se6.azurewebsites.net";

    @GET("/api/UserGoals/{id}")
    UserGoal getUserGoal(@Path("id") long id, @Header("Authorization") String token);

    @GET("/api/UserGoals")
    UserGoal getUserGoal(@Query("userId") String userId, @Query("goalId") long goalId, @Header("Authorization") String token);

    @GET("/api/UserGoals")
    List<UserGoal> getUserGoals(@Header("Authorization") String token);

    @GET("/api/UserGoals")
    List<UserGoal> getUserGoals(@Query("userId") String userId, @Header("Authorization") String token);

    @GET("/api/UserGoals")
    List<UserGoal> getUserGoals(@Query("goalId") long goalId, @Header("Authorization") String token);

    @PUT("/api/UserGoals/{id}")
    Response updateUserGoal(@Path("id") long id, @Body UserGoal userGoal, @Header("Authorization") String token);

    @POST("/api/UserGoals")
    UserGoal createUserGoal(@Body UserGoal UserGoal, @Header("Authorization") String token);

}
