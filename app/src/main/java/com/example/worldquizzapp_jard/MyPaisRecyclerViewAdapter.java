package com.example.worldquizzapp_jard;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.worldquizzapp_jard.PaisFragment.OnListFragmentInteractionListener;
import com.example.worldquizzapp_jard.dummy.DummyContent.DummyItem;
import com.example.worldquizzapp_jard.models.Pais;
import com.example.worldquizzapp_jard.ui.IPaisListener;

import java.nio.file.Path;
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

        holder.mIdView.setText(mValues.get(position).id);

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
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Pais item;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
