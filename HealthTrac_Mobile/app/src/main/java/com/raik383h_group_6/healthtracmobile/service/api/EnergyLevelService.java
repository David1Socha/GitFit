package com.raik383h_group_6.healthtracmobile.service.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raik383h_group_6.healthtracmobile.model.Activity;
import com.raik383h_group_6.healthtracmobile.model.EnergyLevel;

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

public interface EnergyLevelService {
    Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(UPPER_CAMEL_CASE).setDateFormat(ApiConstants.ASPNET_DATE_FORMAT)
            .create();
    Converter DATA_CONVERTER = new GsonConverter(GSON);

    String SERVICE_ENDPOINT = "https://se6.azurewebsites.net";

    @GET("/api/EnergyLevels/{id}")
    EnergyLevel getEnergyLevel(@Path("id") long id, @Header("Authorization") String token);

    @GET("/api/EnergyLevels")
    List<EnergyLevel> getEnergyLevels(@Query("userId") String userId, @Header("Authorization") String token);

    @GET("/api/EnergyLevels")
    List<EnergyLevel> getEnergyLevels(@Header("Authorization") String token);

    @POST("/api/EnergyLevels")
    EnergyLevel createEnergyLevel(@Body EnergyLevel energyLevel, @Header("Authorization") String token);

}
