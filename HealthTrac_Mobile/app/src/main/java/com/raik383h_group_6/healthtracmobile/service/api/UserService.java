package com.raik383h_group_6.healthtracmobile.service.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raik383h_group_6.healthtracmobile.model.User;

import java.util.List;

import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

import static com.google.gson.FieldNamingPolicy.UPPER_CAMEL_CASE;

public interface UserService {
    Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(UPPER_CAMEL_CASE)
            .create();
    Converter DATA_CONVERTER = new GsonConverter(GSON);

    String SERVICE_ENDPOINT = "https://se6.azurewebsites.net";

    @GET("/api/Users/Available")
    boolean isAvailable(@Query("UserName") String userName, @Header("Authorization") String token);

    @GET("/api/Users/{id}")
    User getUser(@Path("id") String id, @Header("Authorization") String token);

    @GET("api/Users")
    List<User> getUsers(@Header("Authorization") String token);

    @PUT("api/Users/{id}")
    void updateUser(@Path("id") String id, @Body User user, @Header("Authorization") String token);
}
