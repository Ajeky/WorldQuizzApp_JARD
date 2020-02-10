package com.example.worldquizzapp_jard;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.PictureDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ViewTarget;
import com.example.worldquizzapp_jard.models.Pais;
import com.example.worldquizzapp_jard.ui.IPaisListener;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;


public class MyPaisRecyclerViewAdapter extends RecyclerView.Adapter<MyPaisRecyclerViewAdapter.ViewHolder> {

    private final List<Pais> listadoPaises;
    Context ctx;
    private int layout;
    private ViewTarget<ImageView, PictureDrawable> requestBuilder;


    public MyPaisRecyclerViewAdapter(List<Pais> listadoPaises, Context ctx, int layout) {
        this.listadoPaises = listadoPaises;
        this.ctx = ctx;
        this.layout = layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_pais, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.item = listadoPaises.get(position);
        if(holder.item.getTranslations().getEs() == null){
            holder.nombrePais.setText(holder.item.getName());
        }
        else {
            holder.nombrePais.setText(holder.item.getTranslations().es);
        }

        Glide.with(ctx)
                .load("https://www.countryflags.io/"+holder.item.getAlpha2Code()+"/flat/64.png")
                .centerCrop()
                .into(holder.urlBandera);




        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IPaisListener iPaisListener = (IPaisListener) ctx;
                iPaisListener.onClickPais(holder.item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listadoPaises.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nombrePais;
        public final ImageView urlBandera;
        public Pais item;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nombrePais = (TextView) view.findViewById(R.id.textNombre);

            urlBandera = view.findViewById(R.id.bandera);

        }
    }
}
