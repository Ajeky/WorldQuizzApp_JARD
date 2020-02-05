package com.example.worldquizzapp_jard;

import com.example.worldquizzapp_jard.models.Foto;
import com.example.worldquizzapp_jard.models.RespuestaUnsplah;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UnsplashService {

    //@GET("photos/random/")
    //Call<Foto> fotosRandom(@Query("client_id") String clientid);

    @GET("/search/photos")
    Call<RespuestaUnsplah> fotosDeUnPais(@Query("query") String pais);


}
