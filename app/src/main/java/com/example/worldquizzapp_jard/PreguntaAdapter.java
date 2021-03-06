package com.example.worldquizzapp_jard;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.worldquizzapp_jard.PreguntaFragment.OnListFragmentInteractionListener;
import com.example.worldquizzapp_jard.models.Pregunta;
import com.example.worldquizzapp_jard.test_quizz.MainActivityQuizz;

import java.util.List;

import static android.app.PendingIntent.getActivity;

public class PreguntaAdapter extends RecyclerView.Adapter<PreguntaAdapter.ViewHolder> {

    private final List<Pregunta> mValues;
    private Context ctx;
    private int layout;
    View view;

    public PreguntaAdapter(Context ctx, int layout, List<Pregunta> items) {
        this.ctx = ctx;
        this.layout = layout;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_pregunta, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.tvEnunciado.setText(holder.mItem.getEnunciado());
        holder.rbRespuesta1.setText(holder.mItem.getRespuesta1());
        holder.rbRespuesta2.setText(holder.mItem.getRespuesta2());
        holder.rbRespuesta3.setText(holder.mItem.getRespuesta3());
        holder.rbRespuesta4.setText(holder.mItem.getRespuesta4());

        if (position == 3) {
            Glide.with(ctx)
                    .load("https://www.countryflags.io/"+holder.mItem.getPais().getAlpha2Code()+"/flat/64.png")
                    .centerCrop()
                    .into(holder.ivBandera);
        }

        ((MainActivityQuizz)ctx).registrarPreguntas(position, holder.mItem);

        holder.rgRespuestas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton respuesta = (RadioButton) holder.rgRespuestas.findViewById(checkedId);

                ((MainActivityQuizz)ctx).registrarRespuestas(position, respuesta.getText().toString());

            }
        });

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
        public final TextView tvEnunciado;
        public final RadioGroup rgRespuestas;
        public final RadioButton rbRespuesta1;
        public final RadioButton rbRespuesta2;
        public final RadioButton rbRespuesta3;
        public final RadioButton rbRespuesta4;
        public final ImageView ivBandera;
        public Pregunta mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvEnunciado = (TextView) view.findViewById(R.id.enunciadoPregunta);
            rgRespuestas = (RadioGroup) view.findViewById(R.id.respuestas);
            rbRespuesta1 = (RadioButton) view.findViewById(R.id.respuesta1);
            rbRespuesta2 = (RadioButton) view.findViewById(R.id.respuesta2);
            rbRespuesta3 = (RadioButton) view.findViewById(R.id.respuesta3);
            rbRespuesta4 = (RadioButton) view.findViewById(R.id.respuesta4);
            ivBandera = (ImageView) view.findViewById(R.id.banderaPregunta);
        }


    }
}
