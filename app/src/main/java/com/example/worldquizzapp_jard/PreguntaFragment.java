package com.example.worldquizzapp_jard;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.worldquizzapp_jard.models.Pais;
import com.example.worldquizzapp_jard.models.Pregunta;
import com.example.worldquizzapp_jard.serviceGenerator.PaisServiceGenerator;
import com.example.worldquizzapp_jard.services.PaisService;
import com.example.worldquizzapp_jard.test_quizz.MainActivityQuizz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class PreguntaFragment extends Fragment {

    // TODO: Customize parameters
    private int mColumnCount = 1;
    private List<Pregunta> preguntas;
    PaisService servicio;
    RecyclerView recyclerView;
    Context ctx;

    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PreguntaFragment() {
    }

    public PreguntaFragment(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pregunta_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            ctx = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(ctx, mColumnCount));
            }


            preguntas = new ArrayList<>();
            servicio = PaisServiceGenerator.createService(PaisService.class);
            new CargarDatos().execute();

        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Pregunta item);
    }

    private class CargarDatos extends AsyncTask<Void, Void, List<Pais>> {

        @Override
        protected List<Pais> doInBackground(Void... voids) {
            List<Pais> result = null;

            Call<List<Pais>> callPaises = servicio.listadoPaises();

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

        @Override
        protected void onPostExecute(List<Pais> paises) {
            int numeroPaises = 10;
            List<Pais> diezPaises, copiaDiezPaises, copiaPaises;
            List<String> respuestas;
            Pregunta pregunta;
            Pais pais = new Pais();
            int random;
            preguntas = new ArrayList<>();


            do {
                diezPaises = new ArrayList<>();
                copiaPaises = paises;
                for (int i = 0; i < numeroPaises; i++) {
                    random = new Random().nextInt(copiaPaises.size());
                    diezPaises.add(copiaPaises.get(random));
                    copiaPaises.remove(random);
                }

                pregunta = generarPreguntaCapitales(diezPaises);

                preguntas.add(pregunta);
            } while (preguntas.size() < 5);

            /*List<String> monedas = new ArrayList<>();
            copiaPaises = paises;

            for (Pais p : copiaPaises) {
                if (!monedas.contains(p.getCurrencies().get(0).getName())) {
                    monedas.add(p.getCurrencies().get(0).getName());
                    Log.d("moneda", p.getCurrencies().get(0).getName() + "");
                }

            }

            Log.d("lista", monedas + "");*/

            recyclerView.setAdapter(new PreguntaAdapter(ctx, R.layout.fragment_pregunta, preguntas));

        }
    }



    public Pregunta generarPreguntaCapitales(List<Pais> diezPaises) {
        Pais pais = new Pais();
        int random;
        List<String> respuestas = new ArrayList<>();
        Pregunta pregunta;
        for (int i = 0; i < 4; i++) {
            random = new Random().nextInt(diezPaises.size());
            pais = diezPaises.get(random);
            if (pais.getCapital().isEmpty() || pais.getCapital() == null) {
                respuestas.add("No tiene");
            } else {
                respuestas.add(pais.getCapital());
            }
            diezPaises.remove(random);
        }

        Collections.shuffle(respuestas);

        pregunta = new Pregunta("¿Cuál es la capital de " + pais.getTranslations().getEs() + "?", respuestas.get(0), respuestas.get(1), respuestas.get(2), respuestas.get(3), pais.getCapital());

        return pregunta;
    }
}
