package com.example.worldquizzapp_jard;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.worldquizzapp_jard.models.Pais;
import com.example.worldquizzapp_jard.serviceGenerator.PaisServiceGenerator;
import com.example.worldquizzapp_jard.services.PaisService;
import com.example.worldquizzapp_jard.ui.IPaisListener;
import com.example.worldquizzapp_jard.utilidades.Constantes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapaEnFragment extends Fragment implements OnMapReadyCallback {
    PaisService service;
    GoogleMap mMap;
    List<Pais> listado;
    IPaisListener listener;
    public MapaEnFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mapa_en, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        service = PaisServiceGenerator.createService(PaisService.class);
        Call<List<Pais>> call = service.listadoPaises();

        call.enqueue(new Callback<List<Pais>>() {
            @Override
            public void onResponse(Call<List<Pais>> call, Response<List<Pais>> response) {
                if(response.isSuccessful()){
                       listado = response.body();

                    for(int i = 0; i<listado.size(); i++){

                        //SI NO ESTA VACIO
                        if(!listado.get(i).getLatlng().isEmpty()){
                            Marker m = mMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(listado.get(i).getLatlng().get(0),listado.get(i).getLatlng().get(1)))
                                    .snippet("Capital: "+listado.get(i).getCapital())
                                    .title(listado.get(i).getTranslations().es));

                            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(listado.get(0).getLatlng().get(0),listado.get(0).getLatlng().get(1))));

                            //m.setTag();






                        }
                    }
                    /*mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {
                            Intent i = new Intent(getActivity(),
                                    DetalleActivity.class);
                            String isoCode = marker.getTag().toString();
                            i.putExtra("", isoCode);

                            startActivity(i);

                            return false;
                        }
                    });*/


                }

            }


            @Override
            public void onFailure(Call<List<Pais>> call, Throwable t) {
                Toast.makeText(getContext(), "Error al realizar la petición", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
