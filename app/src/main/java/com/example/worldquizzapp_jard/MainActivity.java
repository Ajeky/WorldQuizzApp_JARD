package com.example.worldquizzapp_jard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.worldquizzapp_jard.test_quizz.MainActivityQuizz;

import com.example.worldquizzapp_jard.models.Pais;
import com.example.worldquizzapp_jard.ui.IPaisListener;

import com.example.worldquizzapp_jard.models.Usuario;
import com.example.worldquizzapp_jard.rankingAdapter.IUsuarioRankingListener;
import com.example.worldquizzapp_jard.utilidades.Constantes;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import static com.example.worldquizzapp_jard.utilidades.Constantes.ALPHA;
import static com.example.worldquizzapp_jard.utilidades.Constantes.MONEDA;
import static com.example.worldquizzapp_jard.utilidades.Constantes.NOMBRE_CAPITAL;
import static com.example.worldquizzapp_jard.utilidades.Constantes.NOMBRE_PAIS_EN_ESPANOL;
import static com.example.worldquizzapp_jard.utilidades.Constantes.NOMBRE_PAIS_ORIGINAL;
import static com.example.worldquizzapp_jard.utilidades.Constantes.POBLACION;
public class MainActivity extends AppCompatActivity implements IPaisListener, IUsuarioRankingListener {

    FloatingActionButton botonTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_ranking)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        botonTest = (FloatingActionButton)findViewById(R.id.botonTest);

        botonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivityQuizz.class));
            }
        });


    }

    @Override
    public void onClickPais(Pais p) {
        Intent i = new Intent(this,DetalleActivity.class);
        i.putExtra(NOMBRE_CAPITAL,p.getCapital());
        i.putExtra(NOMBRE_PAIS_EN_ESPANOL,p.getTranslations().es);
        i.putExtra(NOMBRE_PAIS_ORIGINAL,p.getName());
        i.putExtra(POBLACION,p.getPopulation().toString());
        i.putExtra(MONEDA, p.getCurrencies().get(0).getName());
        i.putExtra(ALPHA,p.getAlpha2Code());

        startActivity(i);
    }

    @Override
    public void onJugadorClick(Usuario user) {
    }
}
