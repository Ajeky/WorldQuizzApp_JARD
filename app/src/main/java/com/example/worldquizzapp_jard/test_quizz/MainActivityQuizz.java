package com.example.worldquizzapp_jard.test_quizz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.worldquizzapp_jard.PreguntaFragment;
import com.example.worldquizzapp_jard.R;
import com.example.worldquizzapp_jard.models.Pregunta;

public class MainActivityQuizz extends AppCompatActivity implements PreguntaFragment.OnListFragmentInteractionListener {

    Button botonEnviar;
    boolean isButtonBackPressed = false;
    RadioGroup rgRespuesta;
    String respuesta1, respuesta2, respuesta3, respuesta4, respuesta5;
    String respuestaCorrecta1, respuestaCorrecta2, respuestaCorrecta3, respuestaCorrecta4, respuestaCorrecta5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quizz);

        botonEnviar = findViewById(R.id.enviarQuizz);
        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment puntuacionQuiz = EnviarQuizDialogFragment.newInstance(respuesta1, respuesta2, respuesta3, respuesta4, respuesta5, respuestaCorrecta1, respuestaCorrecta2, respuestaCorrecta3, respuestaCorrecta4, respuestaCorrecta5, calcularPuntuacion() + "");
                puntuacionQuiz.show(getSupportFragmentManager(), "enviarQuiz");
            }
        });

    }

    @Override
    public void onListFragmentInteraction(Pregunta item) {

    }

    public void registrarPreguntas(int posicion, Pregunta pregunta) {
        switch (posicion) {
            case 0:
                respuestaCorrecta1 = pregunta.getRespuestaCorrecta();
                break;
            case 1:
                respuestaCorrecta2 = pregunta.getRespuestaCorrecta();
                break;
            case 2:
                respuestaCorrecta3 = pregunta.getRespuestaCorrecta();
                break;
            case 3:
                respuestaCorrecta4 = pregunta.getRespuestaCorrecta();
                break;
            case 4:
                respuestaCorrecta5 = pregunta.getRespuestaCorrecta();
                break;

            default:
                //TODO Tratar el error como se debe tratar
                Log.d("Error", "Algo ha ido mal");
                break;
        }
    }

    public void registrarRespuestas(int posicion, String respuesta) {
        switch (posicion) {
            case 0:
                respuesta1 = respuesta;
                isTestFinalizado();
                break;

            case 1:
                respuesta2 = respuesta;
                isTestFinalizado();
                break;

            case 2:
                respuesta3 = respuesta;
                isTestFinalizado();
                break;

            case 3:
                respuesta4 = respuesta;
                isTestFinalizado();
                break;

            case 4:
                respuesta5 = respuesta;
                isTestFinalizado();
                break;

            default:

                //TODO Tratar el error como se debe tratar
                Log.d("Error", "Algo ha ido mal");
                break;
        }

    }

    private void isTestFinalizado() {
        if (
                respuesta1==null
                || respuesta2==null
                || respuesta3==null
                || respuesta4==null
                || respuesta5==null
        ){

        }else {
            botonEnviar.setEnabled(true);
        }
    }

    public int calcularPuntuacion() {
        int total = 0;

        if (respuestaCorrecta1 == respuesta1) {
            total += 10;
        } if (respuestaCorrecta2 == respuesta2) {
            total += 10;
        } if (respuestaCorrecta3 == respuesta3) {
            total += 10;
        } if (respuestaCorrecta4 == respuesta4) {
            total += 10;
        } if (respuestaCorrecta5 == respuesta5) {
            total += 10;
        }

        return total;
    }

    @Override
    public void onBackPressed() {
        DialogFragment abandonarQuizFragment = new AbandonarQuizDialogFragment();
        abandonarQuizFragment.show(getSupportFragmentManager(),"AbandonarQuizFragment");
    }
}
