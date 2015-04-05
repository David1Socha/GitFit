package com.raik383h_group_6.healthtracmobile.service.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raik383h_group_6.healthtracmobile.model.EnergyLevel;
import com.raik383h_group_6.healthtracmobile.model.Meal;

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

public interface MealService {
    Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(UPPER_CAMEL_CASE).setDateFormat(ApiConstants.ASPNET_DATE_FORMAT)
            .create();
    Converter DATA_CONVERTER = new GsonConverter(GSON);

    String SERVICE_ENDPOINT = "https://se6.azurewebsites.net";

    @GET("/api/Meals/{id}")
    Meal getMeal(@Path("id") long id, @Header("Authorization") String token);

    @GET("/api/Meals")
    List<Meal> getMeals(@Query("userId") String userId, @Header("Authorization") String token);

    @GET("/api/Meals")
    List<Meal> getMeals(@Header("Authorization") String token);

    @POST("/api/Meals")
    Meal createMeal(@Body Meal meal, @Header("Authorization") String token);

}
