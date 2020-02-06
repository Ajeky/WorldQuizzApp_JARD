package com.example.worldquizzapp_jard.rankingAdapter;

import com.example.worldquizzapp_jard.models.Usuario;

import java.util.Comparator;

public class UsuarioRankingComparator implements Comparator<Usuario> {
    @Override
    public int compare(Usuario o1, Usuario o2) {
        return o1.getTotal().compareTo(o2.getTotal());
    }
}
