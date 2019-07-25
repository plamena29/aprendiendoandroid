package com.canplimplam.paisesbanderas.services;

import com.canplimplam.paisesbanderas.JsonPlaceHolderApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private static final JsonPlaceHolderApi JSON_PLACE_HOLDER_API;
    private static final String URL = "https://restcountries.eu/rest/v2/";

    static{

        // Creates the json object which will manage the information received
        GsonBuilder builder = new GsonBuilder();

        /*
        // Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                long millisecons = json.getAsJsonPrimitive().getAsLong();
                return new Date(millisecons);
            }
        });

        builder.registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
            @Override
            public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                long milliseconds = src.getTime();
                return new JsonPrimitive(milliseconds);
            }
        });
*/
        Gson gson = builder.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        JSON_PLACE_HOLDER_API = retrofit.create(JsonPlaceHolderApi.class);
    }

    public static JsonPlaceHolderApi getJsonPlaceHolderApi(){
        return JSON_PLACE_HOLDER_API;
    }

    private RetrofitHelper(){

    }
}
