package com.canplimplam.paisesbanderas;

import com.canplimplam.paisesbanderas.modelo.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    //Countries
    @GET("all")
    Call<List<Country>> getAll();

    @GET("alpha/{id}")
    Call<Country> getById(@Path("id") String alpha2Code);
}
