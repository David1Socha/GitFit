package com.raik383h_group_6.healthtracmobile.service.json;

import com.google.gson.Gson;

public class GsonJsonParserAdapter implements JsonParser{ //Dat class name..

    private Gson gson;

    public GsonJsonParserAdapter(Gson gson) {
        this.gson = gson;
    }

    @Override
    public String toJson(Object src) {
        return gson.toJson(src);
    }

    @Override
    public <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }
}
