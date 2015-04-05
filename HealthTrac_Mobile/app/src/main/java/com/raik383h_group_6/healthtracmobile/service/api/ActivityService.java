package com.raik383h_group_6.healthtracmobile.service.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raik383h_group_6.healthtracmobile.model.Activity;
import com.raik383h_group_6.healthtracmobile.model.Membership;

import java.util.List;

import retrofit.client.Response;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

import static com.google.gson.FieldNamingPolicy.UPPER_CAMEL_CASE;

public interface ActivityService {
    Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(UPPER_CAMEL_CASE).setDateFormat(ApiConstants.ASPNET_DATE_FORMAT)
            .create();
    Converter DATA_CONVERTER = new GsonConverter(GSON);

    String SERVICE_ENDPOINT = "https://se6.azurewebsites.net";

    @GET("/api/Activities/{id}")
    Activity getActivity(@Path("id") long id, @Header("Authorization") String token);

    @GET("/api/Activities")
    List<Activity> getActivities(@Query("userId") String userId, @Header("Authorization") String token);

    @PUT("/api/Activities/{id}")
    Response updateActivity(@Path("id") long id, @Body Activity activity, @Header("Authorization") String token);

    @POST("/api/Activities")
    Activity createActivity (@Body Activity activity, @Header("Authorization") String token);

    @DELETE("/api/Activities/{id}")
    Response deleteActivity(@Path("id") long id, @Header("Authorization") String token);
}
