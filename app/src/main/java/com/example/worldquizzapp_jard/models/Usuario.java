package com.example.worldquizzapp_jard.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private String foto;
    private String nombre;
    private String posicion;
    private int puntos;
    private double puntosPorPartida;

    public Usuario(String foto, String nombre, int puntos, double puntosPorPartida) {
        this.foto = foto;
        this.nombre = nombre;
        this.puntos = puntos;
        this.puntosPorPartida = puntosPorPartida;
    }
}
