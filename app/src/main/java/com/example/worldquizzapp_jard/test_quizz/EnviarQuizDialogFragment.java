package com.example.worldquizzapp_jard.test_quizz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.worldquizzapp_jard.MainActivity;
import com.example.worldquizzapp_jard.R;

public class EnviarQuizDialogFragment extends DialogFragment {

    private static final String RESPUESTA1 = "respuesta1";
    private static final String RESPUESTA2 = "respuesta2";
    private static final String RESPUESTA3 = "respuesta3";
    private static final String RESPUESTA4 = "respuesta4";
    private static final String RESPUESTA5 = "respuesta5";
    private static final String RESPUESTA_CORRECTA1 = "respuestaCorrecta1";
    private static final String RESPUESTA_CORRECTA2 = "respuestaCorrecta2";
    private static final String RESPUESTA_CORRECTA3 = "respuestaCorrecta3";
    private static final String RESPUESTA_CORRECTA4 = "respuestaCorrecta4";
    private static final String RESPUESTA_CORRECTA5 = "respuestaCorrecta5";
    private static final String PUNTOS = "puntos";

    private String respuesta1;
    private String respuesta2;
    private String respuesta3;
    private String respuesta4;
    private String respuesta5;
    private String respuestaCorrecta1;
    private String respuestaCorrecta2;
    private String respuestaCorrecta3;
    private String respuestaCorrecta4;
    private String respuestaCorrecta5;
    private String puntos;

    public static DialogFragment newInstance(String respuesta1, String respuesta2, String respuesta3, String respuesta4, String respuesta5, String respuestaCorrecta1, String respuestaCorrecta2, String respuestaCorrecta3, String respuestaCorrecta4, String respuestaCorrecta5, String puntos) {
        DialogFragment fragment = new EnviarQuizDialogFragment();
        Bundle args = new Bundle();
        args.putString(RESPUESTA1, respuesta1);
        args.putString(RESPUESTA2, respuesta2);
        args.putString(RESPUESTA3, respuesta3);
        args.putString(RESPUESTA4, respuesta4);
        args.putString(RESPUESTA5, respuesta5);
        args.putString(RESPUESTA_CORRECTA1, respuestaCorrecta1);
        args.putString(RESPUESTA_CORRECTA2, respuestaCorrecta2);
        args.putString(RESPUESTA_CORRECTA3, respuestaCorrecta3);
        args.putString(RESPUESTA_CORRECTA4, respuestaCorrecta4);
        args.putString(RESPUESTA_CORRECTA5, respuestaCorrecta5);
        args.putString(PUNTOS, puntos);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        Log.d("onCreateDialog", "Llega a entrar aqui? ");


        if (getArguments() != null) {
            respuesta1 = getArguments().getString(RESPUESTA1);
            respuesta2 = getArguments().getString(RESPUESTA2);
            respuesta3 = getArguments().getString(RESPUESTA3);
            respuesta4 = getArguments().getString(RESPUESTA4);
            respuesta5 = getArguments().getString(RESPUESTA5);
            respuestaCorrecta1 = getArguments().getString(RESPUESTA_CORRECTA1);
            respuestaCorrecta2 = getArguments().getString(RESPUESTA_CORRECTA2);
            respuestaCorrecta3 = getArguments().getString(RESPUESTA_CORRECTA3);
            respuestaCorrecta4 = getArguments().getString(RESPUESTA_CORRECTA4);
            respuestaCorrecta5 = getArguments().getString(RESPUESTA_CORRECTA5);
            puntos = getArguments().getString(PUNTOS);

            Log.d("Respuestas: ", "r1: " + respuesta1 + ", r2: " + respuesta2 + ", r3: " + respuesta3 + ", r4: " + respuesta4 + ", " + respuesta5);
            Log.d("Correctas: ", "r1: " + respuestaCorrecta1 + ", r2: " + respuestaCorrecta2 + ", r3: " + respuestaCorrecta3 + ", r4: " + respuestaCorrecta4 + ", " + respuestaCorrecta5);
            Log.d("Puntos: ", "" + puntos);

            puntos = getArguments().getString(PUNTOS);
        }

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_enviar_quiz, null);

        TextView puntuacion = view.findViewById(R.id.puntuacion);
        puntuacion.setText(puntos + "/50");

        TextView respuesta1bien = (TextView) view.findViewById(R.id.respuesta1bien);
        respuesta1bien.setText(respuestaCorrecta1);

        TextView respuesta2bien = (TextView) view.findViewById(R.id.respuesta2bien);
        respuesta2bien.setText(respuestaCorrecta2);

        TextView respuesta3bien = (TextView) view.findViewById(R.id.respuesta3bien);
        respuesta3bien.setText(respuestaCorrecta3);

        TextView respuesta4bien = (TextView) view.findViewById(R.id.respuesta4bien);
        respuesta4bien.setText(respuestaCorrecta4);

        TextView respuesta5bien = (TextView) view.findViewById(R.id.respuesta5bien);
        respuesta5bien.setText(respuestaCorrecta5);

        TextView respuesta1mal = (TextView) view.findViewById(R.id.respuesta1mal);
        respuesta1mal.setText(respuesta1);

        TextView respuesta2mal = (TextView) view.findViewById(R.id.respuesta2mal);
        respuesta2mal.setText(respuesta2);

        TextView respuesta3mal = (TextView) view.findViewById(R.id.respuesta3mal);
        respuesta3mal.setText(respuesta3);

        TextView respuesta4mal = (TextView) view.findViewById(R.id.respuesta4mal);
        respuesta4mal.setText(respuesta4);

        TextView respuesta5mal = (TextView) view.findViewById(R.id.respuesta5mal);
        respuesta5mal.setText(respuesta5);

        if (respuesta1bien.getText() == respuesta1mal.getText()) {
            respuesta1mal.setVisibility(View.GONE);
        } if (respuesta2bien.getText() == respuesta2mal.getText()) {
            respuesta2mal.setVisibility(View.GONE);
        } if (respuesta3bien.getText() == respuesta3mal.getText()) {
            respuesta3mal.setVisibility(View.GONE);
        } if (respuesta4bien.getText() == respuesta4mal.getText()) {
            respuesta4mal.setVisibility(View.GONE);
        } if (respuesta5bien.getText() == respuesta5mal.getText()) {
            respuesta5mal.setVisibility(View.GONE);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setView(view)
                .setPositiveButton(R.string.title_home, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getActivity(), MainActivity.class) );
                    }
                });

        return builder.create();
    }
}
