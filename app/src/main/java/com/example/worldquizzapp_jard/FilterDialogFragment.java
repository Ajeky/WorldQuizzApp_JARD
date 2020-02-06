package com.example.worldquizzapp_jard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.worldquizzapp_jard.utilidades.Constantes;
import com.example.worldquizzapp_jard.utilidades.IFiltroListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterDialogFragment extends DialogFragment {

    View v;
    ArrayAdapter<String> adapter;
    List<String> listaIdiomas = new ArrayList<>(Arrays.asList("Español","Ingles","Frances"));
    Map<String, String> listaIdiomasMap = new HashMap<>();
    ListView lv;
    //TextView txPais;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_filtro_paises, null);
        builder.setView(v);

        listaIdiomasMap.put("Español","es");
        listaIdiomasMap.put("Ingles","en");

        lv = v.findViewById(R.id.ListViewOpcionesFiltro);

        //txPais = v.findViewById(R.id.txFiltroElemento);

       if (getArguments() != null){
           //Entra en la opcion de filtrar por moneda
           if (getArguments().getString(Constantes.OPCION_FILTRO).equals(Constantes.MONEDA)){
               builder.setTitle(getArguments().getString(Constantes.OPCION_FILTRO));
               //builder.setMessage("Introduza los datos del nuevo alumno");
           //Entra en la opción de filtrar por idioma
           }else if (getArguments().getString(Constantes.OPCION_FILTRO).equals(Constantes.IDIOMA)){
               builder.setTitle(getArguments().getString(Constantes.OPCION_FILTRO));

               adapter = new ArrayAdapter<String>(
                       getActivity(),
                       android.R.layout.simple_list_item_1,
                       listaIdiomas
               );
               lv.setAdapter(adapter);

               //builder.setMessage("Introduza los datos del nuevo alumno");
           }
       }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IFiltroListener iFiltroListener = (IFiltroListener) getTargetFragment();
                iFiltroListener.onClickFiltros(listaIdiomasMap.get(listaIdiomas.get(position)));
                //Toast.makeText(getActivity(), "" + listaIdiomasMap.get(listaIdiomas.get(position)), Toast.LENGTH_SHORT).show();
            }
        });


        return builder.create();
    }
}
