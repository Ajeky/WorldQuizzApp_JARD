package com.example.worldquizzapp_jard.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario  implements Comparable<Usuario> {
    private String foto;
    private String nombre;
    private String posicion;
    private int puntos;
    private double puntosPorPartida;
    private int partidas;

    public Usuario(String foto, String nombre, int puntos, double puntosPorPartida) {
        this.foto = foto;
        this.nombre = nombre;
        this.puntos = puntos;
        this.puntosPorPartida = puntos/partidas;
    }

    @Override
    public int compareTo(Usuario o) {
        if (puntos > o.puntos) {
            return -1;
        }
        if (puntos < o.puntos) {
            return 1;
        }
        return 0;
    }



}
