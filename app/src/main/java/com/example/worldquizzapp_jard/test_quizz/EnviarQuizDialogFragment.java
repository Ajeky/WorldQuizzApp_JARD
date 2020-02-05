package com.example.worldquizzapp_jard.test_quizz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        builder
                .setView(inflater.inflate(R.layout.dialog_enviar_quiz, null))
                .setPositiveButton(R.string.title_home, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getActivity(), MainActivity.class) );
                    }
                });

        return builder.create();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_enviar_quiz, container, false);

        TextView puntuacion = (TextView) view.findViewById(R.id.puntuacion);
        TextView respuesta1bien = (TextView) view.findViewById(R.id.respuesta1bien);
        TextView respuesta2bien = (TextView) view.findViewById(R.id.respuesta2bien);
        TextView respuesta3bien = (TextView) view.findViewById(R.id.respuesta3bien);
        TextView respuesta4bien = (TextView) view.findViewById(R.id.respuesta4bien);
        TextView respuesta5bien = (TextView) view.findViewById(R.id.respuesta5bien);
        TextView respuesta1mal = (TextView) view.findViewById(R.id.respuesta1mal);
        TextView respuesta2mal = (TextView) view.findViewById(R.id.respuesta2mal);
        TextView respuesta3mal = (TextView) view.findViewById(R.id.respuesta3mal);
        TextView respuesta4mal = (TextView) view.findViewById(R.id.respuesta4mal);
        TextView respuesta5mal = (TextView) view.findViewById(R.id.respuesta5mal);


        return view;
    }
}
