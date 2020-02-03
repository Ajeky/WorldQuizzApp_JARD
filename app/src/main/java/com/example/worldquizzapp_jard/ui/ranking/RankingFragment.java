package com.example.worldquizzapp_jard.ui.ranking;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.example.worldquizzapp_jard.rankingAdapter.IUsuarioRankingListener;
import com.example.worldquizzapp_jard.rankingAdapter.MyUsuarioRankingRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RankingFragment extends Fragment{


    // Usuario usuario;
    Context ctx;
    RecyclerView recyclerView;
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private List<Usuario> usuariosList;
    private MyUsuarioRankingRecyclerViewAdapter adapter;
    private RankingViewModel rankingViewModel;
    private IUsuarioRankingListener mListener;


    public RankingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rankingViewModel =
                ViewModelProviders.of(this).get(RankingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_usuario_ranking_list, container, false);

        if (root instanceof RecyclerView) {
            Context context = root.getContext();
            recyclerView = (RecyclerView) root;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

        }
        usuariosList = new ArrayList<>();

        usuariosList.add(new Usuario("https://ep00.epimg.net/elpais/imagenes/2017/10/09/fotorrelato/1507564472_973290_1507564969_album_normal.jpg","Juanma",250,25.6));
        usuariosList.add(new Usuario("https://ep00.epimg.net/elpais/imagenes/2017/10/09/fotorrelato/1507564472_973290_1507564969_album_normal.jpg","Juanma",200,25.6));
        usuariosList.add(new Usuario("https://ep00.epimg.net/elpais/imagenes/2017/10/09/fotorrelato/1507564472_973290_1507564969_album_normal.jpg","Juanma",190,25.6));
        usuariosList.add(new Usuario("https://ep00.epimg.net/elpais/imagenes/2017/10/09/fotorrelato/1507564472_973290_1507564969_album_normal.jpg","Juanma",240,25.6));
        usuariosList.add(new Usuario("https://ep00.epimg.net/elpais/imagenes/2017/10/09/fotorrelato/1507564472_973290_1507564969_album_normal.jpg","Juanma",900,25.6));
        usuariosList.add(new Usuario("https://ep00.epimg.net/elpais/imagenes/2017/10/09/fotorrelato/1507564472_973290_1507564969_album_normal.jpg","Juanma",190,25.6));
        usuariosList.add(new Usuario("https://ep00.epimg.net/elpais/imagenes/2017/10/09/fotorrelato/1507564472_973290_1507564969_album_normal.jpg","Juanma",630,25.6));
        usuariosList.add(new Usuario("https://ep00.epimg.net/elpais/imagenes/2017/10/09/fotorrelato/1507564472_973290_1507564969_album_normal.jpg","Juanma",28,25.6));
        usuariosList.add(new Usuario("https://ep00.epimg.net/elpais/imagenes/2017/10/09/fotorrelato/1507564472_973290_1507564969_album_normal.jpg","Juanma",396,25.6));
        usuariosList.add(new Usuario("https://ep00.epimg.net/elpais/imagenes/2017/10/09/fotorrelato/1507564472_973290_1507564969_album_normal.jpg","Juanma",1050,25.6));

        Collections.sort(usuariosList);


        adapter = new MyUsuarioRankingRecyclerViewAdapter(
                ctx,
                R.layout.fragment_ranking,
                usuariosList
        );


        recyclerView.setAdapter(adapter);
            return root;
        }
    @Override
    public void onAttach(Context context) {
        ctx =context;
        super.onAttach(context);
        if (context instanceof IUsuarioRankingListener) {
            mListener = (IUsuarioRankingListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
