package com.canplimplam.lecturaconstantes2.services;



import com.canplimplam.lecturaconstantes2.model.Lectura;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    //LECTURAS
    @GET("lecturas/{id}")
    Call<List<Lectura>> getLecturas(@Path("id") int userId);

    @Headers("Content-type:application/json")
    @POST("lecturas/{id}")
    public Call<Lectura> create(@Path("id") int userId, @Body Lectura lectura);
}
