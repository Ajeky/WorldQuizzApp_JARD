package com.example.worldquizzapp_jard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.worldquizzapp_jard.models.Foto;
import com.example.worldquizzapp_jard.models.RespuestaUnsplah;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.worldquizzapp_jard.utilidades.Constantes.CONTINENTE;
import static com.example.worldquizzapp_jard.utilidades.Constantes.HORA;
import static com.example.worldquizzapp_jard.utilidades.Constantes.IDIOMA;
import static com.example.worldquizzapp_jard.utilidades.Constantes.MONEDA;
import static com.example.worldquizzapp_jard.utilidades.Constantes.NOMBRE_CAPITAL;
import static com.example.worldquizzapp_jard.utilidades.Constantes.NOMBRE_PAIS_EN_ESPANOL;
import static com.example.worldquizzapp_jard.utilidades.Constantes.NOMBRE_PAIS_ORIGINAL;
import static com.example.worldquizzapp_jard.utilidades.Constantes.POBLACION;

public class DetalleActivity extends AppCompatActivity implements OnMapReadyCallback {

    UnsplashService serviceUnsplash;
    SliderView sliderView;
    List<String> urlsFotos;
    TextView txPais, txCapital, txMoneda, txPoblacion, txIdioma, txContinente, txHora;
    int IMAGENES_A_MOSTRAR = 5;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        initToolbar();

        serviceUnsplash = ServiceGenerator.createService(UnsplashService.class);
        sliderView = findViewById(R.id.imageSlider);
        txCapital = findViewById(R.id.txCapital);
        txPais = findViewById(R.id.txPais);
        txMoneda = findViewById(R.id.txMoneda);
        txPoblacion = findViewById(R.id.txPoblacion);
        txIdioma = findViewById(R.id.txIdioma);
        txContinente = findViewById(R.id.txContinente);
        txHora = findViewById(R.id.txHora);

        txPoblacion.setText(getIntent().getExtras().get(POBLACION).toString());
        txCapital.setText(getIntent().getExtras().get(NOMBRE_CAPITAL).toString());
        txMoneda.setText(getIntent().getExtras().get(MONEDA).toString());
        txPais.setText(getIntent().getExtras().get(NOMBRE_PAIS_EN_ESPANOL).toString());
        txIdioma.setText(getIntent().getExtras().get(IDIOMA).toString());
        txContinente.setText(getIntent().getExtras().get(CONTINENTE).toString());
        txHora.setText(getIntent().getExtras().get(HORA).toString());



        if (txMoneda.getText().length() > txCapital.getText().length() && txMoneda.getText().length() > 20){
            txMoneda.setTextSize(11);
        }

        urlsFotos = new ArrayList<String>();

        final SliderAdapterExample adapter = new SliderAdapterExample(this,urlsFotos);
        //adapter.setCount(3);

        sliderView.setSliderAdapter(adapter);

        //sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        //sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        //sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        //sliderView.setIndicatorSelectedColor(Color.WHITE);
        //sliderView.setIndicatorUnselectedColor(Color.GRAY);
        //sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);
            }
        });

        Call<RespuestaUnsplah> call = serviceUnsplash.fotosDeUnPais(getIntent().getExtras().get(NOMBRE_PAIS_ORIGINAL).toString());

        call.enqueue(new Callback<RespuestaUnsplah>() {
            @Override
            public void onResponse(Call<RespuestaUnsplah> call, Response<RespuestaUnsplah> response) {
                if (response.isSuccessful()){

                    for (int i = 0; i < IMAGENES_A_MOSTRAR ; i++){
                        if (i < response.body().getResults().size()){
                            urlsFotos.add(response.body().getResults().get(i).getUrls().getRegular());
                        }
                    }
                    adapter.notifyDataSetChanged();

                }else {
                    Toast.makeText(DetalleActivity.this, "Error al realizar la petición", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaUnsplah> call, Throwable t) {
                Log.e("Network Failure", t.getMessage());
                Toast.makeText(DetalleActivity.this, "Error al realizar la petición", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        // toolbar.setNavigationIcon(R.drawable.ic_arrow_bac_white_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options_detalle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
