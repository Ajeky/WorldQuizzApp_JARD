package com.example.worldquizzapp_jard.rankingAdapter;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.IntegerArrayAdapter;
import com.example.worldquizzapp_jard.R;
import com.example.worldquizzapp_jard.models.Usuario;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class MyUsuarioRankingRecyclerViewAdapter extends RecyclerView.Adapter<MyUsuarioRankingRecyclerViewAdapter.ViewHolder> {

    private final List<Usuario> mValues;
    private Context ctx;
    private int layout;

    public MyUsuarioRankingRecyclerViewAdapter(Context ctx, int layout, List<Usuario> objects) {
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
        holder.ivCopa.setVisibility(View.INVISIBLE);
        List<Long> resultados = holder.mItem.getResultados();
        long total=  0;
        for (Long elemento : resultados
             ) {
            total+=elemento;
            Log.d(TAG, "----------------------"+String.valueOf(elemento)+"--------------------------");
        }


        /*
        Collection<Double> resultados = holder.mItem.getResultados();

        Iterator it = Collections.emptyIterator();

        while (it.hasNext()) {
            total += (Double) it.next();
        }
*/
       // Double ppp = total/resultados.size();



        holder.tvNombre.setText(holder.mItem.getNombreCompleto());
        holder.tvPosicion.setText(String.valueOf(position+1));
       // holder.tvPuntosPorPartida.setText(String.valueOf(ppp));
        holder.tvPuntos.setText(String.valueOf(total));
        holder.ivGold.setVisibility(View.INVISIBLE);
        holder.ivSilver.setVisibility(View.INVISIBLE);
        holder.ivBronze.setVisibility(View.INVISIBLE);
        holder.ivRibbon.setVisibility(View.INVISIBLE);

        switch (position+1){
            case 1:
                holder.ivGold.setVisibility(View.VISIBLE);
                break;

            case 2:
                holder.ivSilver.setVisibility(View.VISIBLE);
                break;

            case 3:
                holder.ivBronze.setVisibility(View.VISIBLE);
                break;

            default:
                holder.ivRibbon.setVisibility(View.VISIBLE);

        }



        if (position==0){
            holder.ivCorona.setVisibility(View.VISIBLE);

        }else {
            holder.ivCorona.setVisibility(View.INVISIBLE);
        }
        
        Glide
                .with(ctx)
                .load(holder.mItem.getAvatar())
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
        public final ImageView ivGold;
        public final ImageView ivSilver;
        public final ImageView ivBronze;
        public final ImageView ivRibbon;
        public final ImageView ivCopa;
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
            ivGold = view.findViewById(R.id.imageView_medal_gold);
            ivSilver = view.findViewById(R.id.imageView_medal_silver);
            ivBronze = view.findViewById(R.id.imageView_medal_bronze);
            ivRibbon = view.findViewById(R.id.imageView_medal_ribbon);
            ivCopa = view.findViewById(R.id.ranking_copa);


        }


    }
}
