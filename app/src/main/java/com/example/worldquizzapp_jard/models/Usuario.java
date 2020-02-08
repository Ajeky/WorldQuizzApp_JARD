package com.example.worldquizzapp_jard.models;

import android.util.Log;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @NoArgsConstructor @AllArgsConstructor
public class Usuario {

    private String nombreCompleto;
    private String email;
    private List<Long> resultados;
    private String avatar;
    private long total;


    public Usuario(String nombreCompleto, String email, List<Long> resultados, String avatar) {
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.resultados = resultados;
        this.avatar = avatar;


        for (Long elemento : resultados
        ) {
            this.total += elemento;
        }
    }
}
