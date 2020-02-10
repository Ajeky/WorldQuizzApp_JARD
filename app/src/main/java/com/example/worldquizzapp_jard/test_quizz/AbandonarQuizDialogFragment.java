package com.example.worldquizzapp_jard.test_quizz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.worldquizzapp_jard.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Map;

public class AbandonarQuizDialogFragment extends DialogFragment {
    View v;
    private FirebaseFirestore db;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog_abandonar_quiz,null);
        builder.setView(v);

        builder.setTitle("¿Estas seguro que desea abandonar?");
        builder.setMessage("Si abandona tendrá un 0 en este quiz");

        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db = FirebaseFirestore.getInstance();

                db.collection("usuarios")
                        .document(uid)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot usuario = task.getResult();
                                    Map<String, Object> datos = usuario.getData();

                                    List<Integer> resultados = (List<Integer>) datos.get("resultados");
                                    resultados.add(0);

                                    datos.remove("resultados");
                                    datos.put("resultados", resultados);

                                    db.collection("usuarios").document(uid).set(datos);

                                }
                            }
                        });

                getActivity().finish();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });


        return builder.create();
    }
}
