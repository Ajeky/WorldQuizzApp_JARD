package com.example.worldquizzapp_jard;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ViewTarget;
import com.example.worldquizzapp_jard.models.Pais;

import java.util.List;


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
        holder.nombrePais.setText(holder.item.getName());
        holder.nombreCapital.setText(holder.item.getCapital());
        holder.monedaPrincipal.setText(holder.item.getCurrencies().get(0).getName());
        holder.lenguaje.setText(holder.item.getLanguages().get(0).getName());


        //requestBuilder = Glide.with(ctx)
          //      .as(PictureDrawable.class)
            //    .load(holder.item.getFlag())
              //  .listener(new SvgSoftwareLayerSetter())
                //.into(holder.urlBandera);




        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        public final TextView nombreCapital;
        public final TextView monedaPrincipal;
        public final TextView lenguaje;
        public Pais item;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nombrePais = (TextView) view.findViewById(R.id.textNombre);
            nombreCapital = (TextView) view.findViewById(R.id.textCapital);
            monedaPrincipal = (TextView) view.findViewById(R.id.textMoneda);
            urlBandera = view.findViewById(R.id.bandera);
            lenguaje = (TextView) view.findViewById(R.id.textLanguague);
        }
    }
}
