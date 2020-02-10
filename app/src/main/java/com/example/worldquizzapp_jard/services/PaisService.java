package com.example.worldquizzapp_jard.services;


import com.example.worldquizzapp_jard.models.Pais;

import java.util.List;

import lombok.Getter;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PaisService {

    @GET("rest/v2/alpha/{co}")
    Call<Pais> cogerPais(@Path("co") String co);

    @GET("rest/v2/all/")
    Call<List<Pais>> listadoPaises();

    @GET("rest/v2/lang/{et}")
    Call<List<Pais>> listadoPaisesByIdioma(@Path("et") String isoCodeLanguage);

    @GET("rest/v2/currency/{currency}")
    Call<List<Pais>> listadoPaisesByMoneda(@Path("currency") String isoCodeCurrency);
    
    @GET("rest/v2/alpha/{codigo}")
    Call<Pais> nombrePais(@Path("codigo") String codigo);

}
