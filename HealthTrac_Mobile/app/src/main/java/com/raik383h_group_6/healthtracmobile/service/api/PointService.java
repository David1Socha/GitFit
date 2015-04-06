package com.raik383h_group_6.healthtracmobile.service.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raik383h_group_6.healthtracmobile.model.Meal;
import com.raik383h_group_6.healthtracmobile.model.Point;

import java.util.List;

import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

import static com.google.gson.FieldNamingPolicy.UPPER_CAMEL_CASE;

public interface PointService {
    Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(UPPER_CAMEL_CASE).setDateFormat(ApiConstants.ASPNET_DATE_FORMAT)
            .create();
    Converter DATA_CONVERTER = new GsonConverter(GSON);

    String SERVICE_ENDPOINT = "https://se6.azurewebsites.net";

    @GET("/api/Points/{id}")
    Point getPoint(@Path("id") long id, @Header("Authorization") String token);

    @GET("/api/Points")
    List<Point> getPoints(@Query("activityId") long activityId, @Header("Authorization") String token);

    @GET("/api/Points")
    List<Point> getPoints(@Header("Authorization") String token);

    @POST("/api/Points")
    Point createPoint(@Body Point point, @Header("Authorization") String token);

    @POST("/api/CreatePoints")
    List<Point> createPoints(@Body List<Point> points, @Header("Authorization") String token); //TODO if erroring out, might need to change from list to array for deserialization

}
