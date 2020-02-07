package com.example.worldquizzapp_jard;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.worldquizzapp_jard.models.Pais;
import com.example.worldquizzapp_jard.serviceGenerator.PaisServiceGenerator;
import com.example.worldquizzapp_jard.services.PaisService;
import com.example.worldquizzapp_jard.ui.IPaisListener;
import com.example.worldquizzapp_jard.utilidades.Constantes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class PaisFragment extends Fragment implements IFiltroListener {


    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private IPaisListener mListener;
    private List<Pais> listadoPais;
    private MyPaisRecyclerViewAdapter adapter;
    PaisService service;
    Context ctx;
    RecyclerView recyclerView;
    boolean filtroActivo = false;
    MenuItem itemLimpiarFiltro;

    public PaisFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pais_list, container, false);


        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }


            listadoPais = new ArrayList<>();

            service = PaisServiceGenerator.createService(PaisService.class);
            new LoadDataTask().execute();

        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ctx = context;
        if (context instanceof IPaisListener) {
            mListener = (IPaisListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement IPaisListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void cargarDatos(List<Pais> lista) {

        recyclerView.setAdapter(new MyPaisRecyclerViewAdapter(lista, ctx, R.layout.fragment_pais));
    }

    @Override
    public void onClickFiltros(String filtro,String tipo) {
        itemLimpiarFiltro.setVisible(true);
        new LoadDataTask().execute(filtro,tipo);
        //Toast.makeText(ctx, "En Pais fragment mirando tuto " + filtro, Toast.LENGTH_SHORT).show();
    }

    private class LoadDataTask extends AsyncTask<String, Void, List<Pais>> {

        protected List<Pais> doInBackground(String... strings) {

            List<Pais> result = null;
            Call<List<Pais>> callPaises = null;
            Response<List<Pais>> responsePaises = null;

            //Este if es para diferenciar y cargar toda la lista de paises o cargar una lista según el filtro que le pasamos por parámetro en el AsyncTask
            if (strings.length != 0){
                //Este if difenencia si el usuario ha filtrado por moneda o por idioma
                if (strings[1].equals(Constantes.MONEDA)){
                    callPaises = service.listadoPaisesByMoneda(strings[0]);
                }else if(strings[1].equals(Constantes.IDIOMA)){
                    callPaises = service.listadoPaisesByIdioma(strings[0]);
                }
            }else {
                callPaises = service.listadoPaises();
            }

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

        @Override
        protected void onPostExecute(List<Pais> paises) {
            if (paises != null)
                cargarDatos(paises);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_opciones_lista_paises,menu);
        itemLimpiarFiltro = menu.findItem(R.id.borrar_filtro);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.filtro_moneda:
                DialogFragment dialogMoneda = new FilterDialogFragment();
                Bundle bundleMoneda = new Bundle();
                bundleMoneda.putString(Constantes.OPCION_FILTRO,Constantes.MONEDA);
                dialogMoneda.setArguments(bundleMoneda);
                dialogMoneda.setTargetFragment(this,0);
                dialogMoneda.show(getFragmentManager(),"FiltroMonedaFragment");
                break;
            case R.id.filtro_idioma:
                DialogFragment dialogIdioma = new FilterDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString(Constantes.OPCION_FILTRO,Constantes.IDIOMA);
                dialogIdioma.setArguments(bundle);
                dialogIdioma.setTargetFragment(this,0);
                dialogIdioma.show(getFragmentManager(),"FiltroIdiomaFragment");
                break;
            case R.id.borrar_filtro:
                new LoadDataTask().execute();
                item.setVisible(false);
                filtroActivo=false;
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

