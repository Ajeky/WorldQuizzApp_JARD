package com.example.worldquizzapp_jard.test_quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.worldquizzapp_jard.PreguntaFragment;
import com.example.worldquizzapp_jard.R;
import com.example.worldquizzapp_jard.models.Pregunta;

public class MainActivityQuizz extends AppCompatActivity implements PreguntaFragment.OnListFragmentInteractionListener {

    Button botonEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quizz);

        botonEnviar = findViewById(R.id.enviarQuizz);
        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public void onListFragmentInteraction(Pregunta item) {

    }
}
