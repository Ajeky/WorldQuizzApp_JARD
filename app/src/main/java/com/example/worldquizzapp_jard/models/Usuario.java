package com.example.worldquizzapp_jard.models;

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
}
