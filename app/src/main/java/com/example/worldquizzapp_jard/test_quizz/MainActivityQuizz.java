package com.example.worldquizzapp_jard.test_quizz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.worldquizzapp_jard.PreguntaFragment;
import com.example.worldquizzapp_jard.R;
import com.example.worldquizzapp_jard.models.Pregunta;

public class MainActivityQuizz extends AppCompatActivity implements PreguntaFragment.OnListFragmentInteractionListener {

    Button botonEnviar;
    RadioGroup rgRespuesta;
    String respuesta1, respuesta2, respuesta3, respuesta4, respuesta5;
    Pregunta pregunta1, pregunta2, pregunta3, pregunta4, pregunta5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quizz);

        botonEnviar = findViewById(R.id.enviarQuizz);
        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment puntuacionQuiz = new EnviarQuizDialogFragment();
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
                pregunta1 = pregunta;
                break;
            case 1:
                pregunta2 = pregunta;
                break;
            case 2:
                pregunta3 = pregunta;
                break;
            case 3:
                pregunta4 = pregunta;
                break;
            case 4:
                pregunta5 = pregunta;
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
                break;

            case 1:
                respuesta2 = respuesta;
                break;

            case 2:
                respuesta3 = respuesta;
                break;

            case 3:
                respuesta4 = respuesta;
                break;

            case 4:
                respuesta5 = respuesta;
                break;

            default:
                //TODO Tratar el error como se debe tratar
                Log.d("Error", "Algo ha ido mal");
                break;
        }

    }

}
