package com.example.worldquizzapp_jard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.example.worldquizzapp_jard.login.LogInActivity;
import com.example.worldquizzapp_jard.test_quizz.MainActivityQuizz;

import com.example.worldquizzapp_jard.models.Pais;
import com.example.worldquizzapp_jard.ui.IPaisListener;

import com.example.worldquizzapp_jard.models.Usuario;
import com.example.worldquizzapp_jard.rankingAdapter.IUsuarioRankingListener;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import static com.example.worldquizzapp_jard.utilidades.Constantes.ALPHA;


import static com.example.worldquizzapp_jard.utilidades.Constantes.CONTINENTE;
import static com.example.worldquizzapp_jard.utilidades.Constantes.HORA;
import static com.example.worldquizzapp_jard.utilidades.Constantes.IDIOMA;
import static com.example.worldquizzapp_jard.utilidades.Constantes.LATITUD;
import static com.example.worldquizzapp_jard.utilidades.Constantes.LONGITUD;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_options_menu, menu);
        final MenuItem settingsItem = menu.findItem(R.id.profile);

        Glide
                .with(this)
                .asBitmap()
                .load(account.getPhotoUrl().toString())
                .circleCrop()
                .into(new CustomTarget<Bitmap>(100,100) {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                settingsItem.setIcon(new BitmapDrawable(getResources(), resource));
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.profile:
                // TODO goToProfile();
                break;
            case R.id.log_out:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, LogInActivity.class));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    public void onClickPais(Pais p) {
        Intent i = new Intent(this,DetalleActivity.class);
        i.putExtra(ALPHA,p.getAlpha2Code());
        i.putExtra(NOMBRE_PAIS_ORIGINAL, p.getName());
        //Hay paises en la api que no tienen longitud ni latitud
        if (p.getLatlng().size() <= 1 || p.getLatlng().size() <= 1){
            i.putExtra(LATITUD,0);
            i.putExtra(LONGITUD,0);
        } else {
            i.putExtra(LATITUD,p.getLatlng().get(0));
            i.putExtra(LONGITUD,p.getLatlng().get(1));
        }
        startActivity(i);
    }

    @Override
    public void onJugadorClick(Usuario user) {
    }

}
