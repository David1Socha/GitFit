package com.raik383h_group_6.healthtracmobile.service.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raik383h_group_6.healthtracmobile.model.Membership;

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

public interface MembershipService {
    Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(UPPER_CAMEL_CASE)
            .create();
    Converter DATA_CONVERTER = new GsonConverter(GSON);

    String SERVICE_ENDPOINT = "https://se6.azurewebsites.net";

    @GET("/api/Memberships/{id}")
    Membership getMembership(@Path("id") long id, @Header("Authorization") String token);

    @GET("api/Memberships")
    Membership getMembership(@Query("userId") String userId, @Query("teamId") long teamId, @Header("Authorization") String token);

    @GET("api/Memberships")
    List<Membership> getMemberships(@Header("Authorization") String token);

    @GET("api/Memberships")
    List<Membership> getMemberships(@Query("userId") String userId, @Header("Authorization") String token);

    @GET("api/Memberships")
    List<Membership> getMemberships(@Query("teamId") long teamId, @Header("Authorization") String token);

    @PUT("api/Memberships/{id}")
    Response updateMembership(@Path("id") long id, @Body Membership membership, @Header("Authorization") String token);

    @POST("api/Memberships")
    Membership createMembership(@Body Membership membership, @Header("Authorization") String token);
}
