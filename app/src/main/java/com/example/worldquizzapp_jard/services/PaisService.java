package com.example.worldquizzapp_jard.services;


import com.example.worldquizzapp_jard.models.Pais;

import java.util.List;

import lombok.Getter;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PaisService {

    @GET("rest/v2/all/")
    Call<List<Pais>> listadoPaises();

    @GET("rest/v2/alpha/{codigo}")
    Call<Pais> nombrePais(@Path("codigo") String codigo);

}
