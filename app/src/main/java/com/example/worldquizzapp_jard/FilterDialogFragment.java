package com.example.worldquizzapp_jard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.worldquizzapp_jard.utilidades.Constantes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterDialogFragment extends DialogFragment {

    View v;
    ArrayAdapter<String> adapter;
    List<String> listaIdiomas = new ArrayList<>(Arrays.asList("Español","Ingles","Frances"));
    List<String> listaMonedas = new ArrayList<>(Arrays.asList("Euro","Dolar Estadounidense","Libra Esterlina"));
    Map<String, String> listaIdiomasMap = new HashMap<>();
    Map<String, String> listaMonedasMap = new HashMap<>();
    ListView lv;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_filtro_paises, null);
        builder.setView(v);

        listaIdiomasMap.put("Español","es");
        listaIdiomasMap.put("Ingles","en");
        listaIdiomasMap.put("Frances","fr");

        listaMonedasMap.put("Euro","eur");
        listaMonedasMap.put("Dolar Estadounidense","usd");
        listaMonedasMap.put("Libra Esterlina","gbp");


        lv = v.findViewById(R.id.ListViewOpcionesFiltro);

        //txPais = v.findViewById(R.id.txFiltroElemento);

       if (getArguments() != null){
           //Entra en la opcion de filtrar por moneda
           if (getArguments().getString(Constantes.OPCION_FILTRO).equals(Constantes.MONEDA)){
               builder.setTitle("Filtrar por moneda");

               adapter = new ArrayAdapter<String>(
                       getActivity(),
                       android.R.layout.simple_list_item_1,
                       listaMonedas
               );
               lv.setAdapter(adapter);

               //builder.setMessage("Introduza los datos del nuevo alumno");

           //Entra en la opción de filtrar por idioma
           }else if (getArguments().getString(Constantes.OPCION_FILTRO).equals(Constantes.IDIOMA)){
               builder.setTitle("Filtrar por idioma");

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

                if (getArguments() != null){
                    if (getArguments().getString(Constantes.OPCION_FILTRO).equals(Constantes.MONEDA)){
                        iFiltroListener.onClickFiltros(listaMonedasMap.get(listaMonedas.get(position)),Constantes.MONEDA);
                    }else if (getArguments().getString(Constantes.OPCION_FILTRO).equals(Constantes.IDIOMA)){
                        iFiltroListener.onClickFiltros(listaIdiomasMap.get(listaIdiomas.get(position)),Constantes.IDIOMA);
                    }
                }

                dismiss();
                //Toast.makeText(getActivity(), "" + listaIdiomasMap.get(listaIdiomas.get(position)), Toast.LENGTH_SHORT).show();
            }
        });


        return builder.create();
    }
}
