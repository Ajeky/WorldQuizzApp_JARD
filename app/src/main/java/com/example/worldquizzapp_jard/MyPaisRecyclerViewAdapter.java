package com.example.worldquizzapp_jard;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.worldquizzapp_jard.models.Pais;
import com.example.worldquizzapp_jard.ui.IPaisListener;
import java.util.List;


public class MyPaisRecyclerViewAdapter extends RecyclerView.Adapter<MyPaisRecyclerViewAdapter.ViewHolder> {

    private final List<Pais> listadoPaises;
    private final Context ctx;
    private final IPaisListener mListener;


    public MyPaisRecyclerViewAdapter(List<Pais> listadoPaises, Context ctx, IPaisListener mListener) {
        this.listadoPaises = listadoPaises;
        this.ctx = ctx;
        this.mListener = mListener;
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
        holder.nombrePais.setText(listadoPaises.get(position).getName());
        holder.nombreCapital.setText(listadoPaises.get(position).getCapital());
        holder.monedaPrincipal.setText(listadoPaises.get(position).getCurrencies().get(0).getName());
        holder.lenguaje.setText(listadoPaises.get(position).getLanguages().get(0).getName());

        Glide.with(ctx)
                .load(listadoPaises.get(position).getFlag())
                .into(holder.urlBandera);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onClickPais(holder.item);
                }
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
            nombrePais = view.findViewById(R.id.nombrePais);
            urlBandera = view.findViewById(R.id.urlBandera);
            nombreCapital = view.findViewById(R.id.nombreCapital);
            monedaPrincipal = view.findViewById(R.id.monedaPrincipal);
            lenguaje = view.findViewById(R.id.lenguajePrincipal);
        }
    }
}
