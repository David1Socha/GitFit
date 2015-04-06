package com.raik383h_group_6.healthtracmobile.service.json;

public interface JsonParser {

    String toJson(Object src);
    <T> T fromJson(String json, Class<T> classOfT);
}
