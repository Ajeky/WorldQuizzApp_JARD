package com.example.worldquizzapp_jard;

import androidx.fragment.app.FragmentActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.worldquizzapp_jard.models.Pais;
import com.example.worldquizzapp_jard.serviceGenerator.PaisServiceGenerator;
import com.example.worldquizzapp_jard.services.PaisService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    PaisService service;
    private GoogleMap mMap;
    private List<Pais> paises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        paises = new ArrayList<Pais>();
        service = PaisServiceGenerator.createService(PaisService.class);
        new LoadDataMap().execute();


        for(int i = 0; i<paises.size(); i++){
            LatLng coord = new LatLng(paises.get(i).getLatlng().get(0),paises.get(i).getLatlng().get(1));
            mMap.addMarker(new MarkerOptions().position(coord).title(paises.get(i).getName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(paises.get(0).getLatlng().get(0),paises.get(0).getLatlng().get(1))));


        }


    }

    private class LoadDataMap extends AsyncTask<Void, Void, List<Pais>> {

        protected List<Pais> doInBackground(Void... voids) {
            List<Pais> result = null;

            Call<List<Pais>> callPaises = service.listadoPaises();

            Response<List<Pais>> responsePaises = null;

            try {
                responsePaises = callPaises.execute();
            } catch (IOException e){
                e.printStackTrace();
            }

            if (responsePaises.isSuccessful()) {
                result = responsePaises.body();
            }
            return result;
        }

    }
}