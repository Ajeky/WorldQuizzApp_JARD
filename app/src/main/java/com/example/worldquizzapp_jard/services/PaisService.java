package com.example.worldquizzapp_jard.services;


import com.example.worldquizzapp_jard.models.Pais;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PaisService {

    @GET("/")
    Call<List<Pais>> listadoPaises();

}
