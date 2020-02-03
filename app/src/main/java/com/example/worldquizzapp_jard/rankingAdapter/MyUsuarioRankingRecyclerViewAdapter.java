package com.example.worldquizzapp_jard.rankingAdapter;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.worldquizzapp_jard.R;
import com.example.worldquizzapp_jard.models.Usuario;

import java.util.List;


public class MyUsuarioRankingRecyclerViewAdapter extends RecyclerView.Adapter<MyUsuarioRankingRecyclerViewAdapter.ViewHolder> {

    private final List<Usuario> mValues;
    private Context ctx;
    private int layout;

    public MyUsuarioRankingRecyclerViewAdapter(Context ctx, int layout, List<Usuario > objects) {
        this.ctx = ctx;
        this.layout = layout;
        mValues = objects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_usuario_ranking, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvNombre.setText(holder.mItem.getNombre());
        holder.tvPosicion.setText(String.valueOf(position+1));
        holder.tvPuntosPorPartida.setText(String.valueOf(holder.mItem.getPuntosPorPartida()));
        holder.tvPuntos.setText(String.valueOf(holder.mItem.getPuntos()));

        if (position==0){
            holder.ivCorona.setVisibility(View.VISIBLE);

        }else {
            holder.ivCorona.setVisibility(View.INVISIBLE);
        }
        
        Glide
                .with(ctx)
                .load(holder.mItem.getFoto())
                .centerCrop()
                .into(holder.ivAvatar);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvNombre;
        public final TextView tvPosicion;
        public final TextView tvPuntos;
        public final TextView tvPuntosPorPartida;
        public final ImageView ivAvatar;
        public final ImageView ivCorona;
        public Usuario mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvNombre = view.findViewById(R.id.ranking_nombre);
            tvPosicion = view.findViewById(R.id.ranking_posicion);
            tvPuntos = view.findViewById(R.id.ranking_puntos);
            tvPuntosPorPartida = view.findViewById(R.id.ranking_puntosPorPartida);
            ivAvatar = view.findViewById(R.id.ranking_avatar);
            ivCorona = view.findViewById(R.id.ranking_corona);


        }


    }
}
