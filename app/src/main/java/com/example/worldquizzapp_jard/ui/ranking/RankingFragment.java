package com.example.worldquizzapp_jard.ui.ranking;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldquizzapp_jard.R;
import com.example.worldquizzapp_jard.models.Usuario;
import com.example.worldquizzapp_jard.rankingAdapter.MyUsuarioRankingRecyclerViewAdapter;

import java.util.List;

public class RankingFragment extends Fragment {


    // Usuario usuario;
    Context ctx;
    RecyclerView recyclerView;
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private List<Usuario> usuariosList;
    private MyUsuarioRankingRecyclerViewAdapter adapter;
    private RankingViewModel rankingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rankingViewModel =
                ViewModelProviders.of(this).get(RankingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ranking, container, false);

        if (root instanceof RecyclerView) {
            Context context = root.getContext();
            recyclerView = (RecyclerView) root;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            usuariosList.add(new Usuario("https://ep00.epimg.net/elpais/imagenes/2017/10/09/fotorrelato/1507564472_973290_1507564969_album_normal.jpg","Juanma",250,25.6));
            usuariosList.add(new Usuario("https://ep00.epimg.net/elpais/imagenes/2017/10/09/fotorrelato/1507564472_973290_1507564969_album_normal.jpg","Juanma",250,25.6));
            usuariosList.add(new Usuario("https://ep00.epimg.net/elpais/imagenes/2017/10/09/fotorrelato/1507564472_973290_1507564969_album_normal.jpg","Juanma",250,25.6));
            usuariosList.add(new Usuario("https://ep00.epimg.net/elpais/imagenes/2017/10/09/fotorrelato/1507564472_973290_1507564969_album_normal.jpg","Juanma",250,25.6));
            usuariosList.add(new Usuario("https://ep00.epimg.net/elpais/imagenes/2017/10/09/fotorrelato/1507564472_973290_1507564969_album_normal.jpg","Juanma",250,25.6));
            usuariosList.add(new Usuario("https://ep00.epimg.net/elpais/imagenes/2017/10/09/fotorrelato/1507564472_973290_1507564969_album_normal.jpg","Juanma",250,25.6));
            usuariosList.add(new Usuario("https://ep00.epimg.net/elpais/imagenes/2017/10/09/fotorrelato/1507564472_973290_1507564969_album_normal.jpg","Juanma",250,25.6));
            usuariosList.add(new Usuario("https://ep00.epimg.net/elpais/imagenes/2017/10/09/fotorrelato/1507564472_973290_1507564969_album_normal.jpg","Juanma",250,25.6));
            usuariosList.add(new Usuario("https://ep00.epimg.net/elpais/imagenes/2017/10/09/fotorrelato/1507564472_973290_1507564969_album_normal.jpg","Juanma",250,25.6));
            usuariosList.add(new Usuario("https://ep00.epimg.net/elpais/imagenes/2017/10/09/fotorrelato/1507564472_973290_1507564969_album_normal.jpg","Juanma",250,25.6));

            adapter = new MyUsuarioRankingRecyclerViewAdapter(
                    ctx,
                    R.layout.fragment_ranking,
                    usuariosList
            );


            recyclerView.setAdapter(adapter);

        }
            return root;
        }

}
