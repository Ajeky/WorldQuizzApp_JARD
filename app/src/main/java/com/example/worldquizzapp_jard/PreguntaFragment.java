package com.example.worldquizzapp_jard;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.service.notification.NotificationListenerService;
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
import java.lang.reflect.Array;
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
    List<Pais> copiaPaises;
    Pais paisFrontera;

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
            } catch (IOException e) {
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
            List<Pais> diezPaises, copiaDiezPaises;
            Pregunta pregunta;
            int random;
            preguntas = new ArrayList<>();
            List<String> monedas = generarListaMonedas(paises);

            diezPaises = new ArrayList<>();
            copiaPaises = new ArrayList<>();

            copiaPaises.addAll(paises);
            for (int i = 0; i < numeroPaises; i++) {
                random = new Random().nextInt(copiaPaises.size());
                diezPaises.add(copiaPaises.get(random));
                copiaPaises.remove(random);
            }

            copiaPaises.removeAll(copiaPaises);
            copiaPaises.addAll(paises);

            copiaDiezPaises = new ArrayList<>();
            copiaDiezPaises.removeAll(copiaDiezPaises);
            copiaDiezPaises.addAll(diezPaises);

            pregunta = generarPreguntaCapitales(copiaDiezPaises);
            preguntas.add(pregunta);

            copiaDiezPaises.removeAll(copiaDiezPaises);
            copiaDiezPaises.addAll(diezPaises);

            pregunta = generarPreguntaMoneda(copiaDiezPaises, monedas);
            preguntas.add(pregunta);

            copiaDiezPaises.removeAll(copiaDiezPaises);
            copiaDiezPaises.addAll(diezPaises);

            pregunta = generarPreguntaIdiomas();
            preguntas.add(pregunta);

            copiaPaises.removeAll(copiaPaises);
            copiaPaises.addAll(paises);

            pregunta = generarPreguntaBandera(copiaDiezPaises);
            preguntas.add(pregunta);

            copiaDiezPaises.removeAll(copiaDiezPaises);
            copiaDiezPaises.addAll(diezPaises);

            generarPreguntaFrontera(copiaDiezPaises);


        }
    }


    public Pregunta generarPreguntaCapitales(List<Pais> diezPaises) {
        Pais pais = new Pais();
        int random;
        List<String> respuestas = new ArrayList<>();
        Pregunta pregunta;

        do {
            random = new Random().nextInt(diezPaises.size());
            pais = diezPaises.get(random);

            if (pais.getTranslations().getEs() != null && !pais.getTranslations().getEs().isEmpty()) {
                if (pais.getCapital().isEmpty() || pais.getCapital() == null) {
                    respuestas.add("No tiene");
                } else {
                    respuestas.add(pais.getCapital());
                }
            }
            diezPaises.remove(random);
        } while (respuestas.size() < 4);

        Collections.shuffle(respuestas);

        pregunta = new Pregunta("¿Cuál es la capital de " + pais.getTranslations().getEs() + "?", respuestas.get(0), respuestas.get(1), respuestas.get(2), respuestas.get(3), pais.getCapital(), pais);

        ((MainActivityQuizz) getActivity()).registrarPreguntas(0, pregunta);


        if (pregunta.getRespuestaCorrecta().isEmpty()) {
            pregunta.setRespuestaCorrecta("No tiene");
        }

        return pregunta;
    }

    public Pregunta generarPreguntaMoneda(List<Pais> diezPaises, List<String> monedas) {
        Pais pais;
        int random;
        List<String> respuestas = new ArrayList<>();
        Pregunta pregunta;

        do {
            random = new Random().nextInt(diezPaises.size());
            pais = diezPaises.get(random);
            if (!pais.getCurrencies().get(0).getName().contains("[")) {
                respuestas.add(pais.getCurrencies().get(0).getName());
                monedas.remove(respuestas.get(0));
            }
        } while (respuestas.size() < 1);

        do {
            random = new Random().nextInt(monedas.size());
            respuestas.add(monedas.get(random));
            monedas.remove(monedas.get(random));
        } while (respuestas.size() < 4);

        Collections.shuffle(respuestas);

        pregunta = new Pregunta("¿Cuál es la moneda de " + pais.getTranslations().getEs() + " ?", respuestas.get(0), respuestas.get(1), respuestas.get(2), respuestas.get(3), pais.getCurrencies().get(0).getName(), pais);

        ((MainActivityQuizz) getActivity()).registrarPreguntas(1, pregunta);

        return pregunta;
    }


    public List<String> generarListaMonedas(List<Pais> paises) {
        List<String> monedas = new ArrayList<>();

        for (Pais p : paises) {
            if (!monedas.contains(p.getCurrencies().get(0).getName()) && !p.getCurrencies().get(0).getName().contains("[")) {
                monedas.add(p.getCurrencies().get(0).getName());
            }
        }

        return monedas;
    }

    public Pregunta generarPreguntaBandera(List<Pais> diezPaises) {
        int random;
        Pais pais = new Pais();
        List<String> respuestas = new ArrayList<>();
        Pregunta pregunta;

        do {
            random = new Random().nextInt(diezPaises.size());
            pais = diezPaises.get(random);
            if (pais.getTranslations().getEs() != null && !pais.getTranslations().getEs().isEmpty()) {
                respuestas.add(pais.getTranslations().getEs());
            }
            diezPaises.remove(random);
        } while (respuestas.size() < 4);

        Collections.shuffle(respuestas);

        pregunta = new Pregunta("¿A qué país pertenece la siguiente bandera?", respuestas.get(0), respuestas.get(1), respuestas.get(2), respuestas.get(3), pais.getTranslations().getEs(), pais);

        ((MainActivityQuizz) getActivity()).registrarPreguntas(3, pregunta);

        return pregunta;
    }

    public Pregunta generarPreguntaIdiomas() {
        Pais pais;
        int random;
        List<String> respuestas = new ArrayList<>();
        Pregunta pregunta;
        String idioma;

        do {
            random = new Random().nextInt(copiaPaises.size());
            pais = copiaPaises.get(random);
            idioma = pais.getLanguages().get(0).getName();
            if (!respuestas.contains(idioma)) {
                respuestas.add(idioma);
            }
            copiaPaises.remove(random);

        } while (respuestas.size() < 4);

        Collections.shuffle(respuestas);

        pregunta = new Pregunta("¿Qué idioma se habla mayoritariamente en " + pais.getTranslations().getEs() + "?", respuestas.get(0), respuestas.get(1), respuestas.get(2), respuestas.get(3), pais.getLanguages().get(0).getName(), pais);

        ((MainActivityQuizz) getActivity()).registrarPreguntas(4, pregunta);

        return pregunta;
    }

    public void generarPreguntaFrontera(List<Pais> diezPaises) {
        int random;
        List<String> respuestas = new ArrayList<>();
        Pregunta pregunta;
        String codigoPais;

        random = new Random().nextInt(diezPaises.size());
        paisFrontera = diezPaises.get(random);
        diezPaises.remove(random);

        if (paisFrontera.getBorders().isEmpty() || paisFrontera.getBorders() == null) {
            respuestas.add("No tiene países limítrofes.");
            do {
                random = new Random().nextInt(diezPaises.size());
                respuestas.add(diezPaises.get(random).getTranslations().getEs());
                diezPaises.remove(random);
            } while (respuestas.size() < 4);

            Collections.shuffle(respuestas);
            preguntas.add(new Pregunta("¿Que país es limítrofe de " + paisFrontera.getTranslations().getEs() + "?", respuestas.get(0), respuestas.get(1), respuestas.get(2), respuestas.get(3), "No tiene países limítrofes.", paisFrontera));
            recyclerView.setAdapter(new PreguntaAdapter(ctx, R.layout.fragment_pregunta, preguntas));

        } else {
            random = new Random().nextInt(paisFrontera.getBorders().size());
            codigoPais = paisFrontera.getBorders().get(random);

            new cargarPreguntaFrontera().execute(codigoPais);
        }
    }

    public class cargarPreguntaFrontera extends AsyncTask<String, Void, Pais> {

        @Override
        protected Pais doInBackground(String... strings) {
            Pais result = new Pais();
            Call<Pais> callPais = servicio.nombrePais(strings[0]);

            Response<Pais> responsePais = null;
            try {
                responsePais = callPais.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (responsePais.isSuccessful()) {
                result = responsePais.body();
            }

            return result;
        }

        @Override
        protected void onPostExecute(Pais pais) {
            List<String> respuestas = new ArrayList<>();
            int random;
            String respCorrecta;

            respCorrecta = pais.getTranslations().getEs();
            respuestas.add(respCorrecta);

            do {
                random = new Random().nextInt(copiaPaises.size());
                pais = copiaPaises.get(random);

                if (pais != paisFrontera && !respuestas.contains(pais.getTranslations().getEs())) {
                    respuestas.add(pais.getTranslations().getEs());
                }

                copiaPaises.remove(random);

            } while (respuestas.size() < 4);

            Collections.shuffle(respuestas);
            preguntas.add(new Pregunta("¿Qué país es limítrofe de " + paisFrontera.getTranslations().getEs() + "?", respuestas.get(0), respuestas.get(1), respuestas.get(2), respuestas.get(3), respCorrecta, paisFrontera));

            recyclerView.setAdapter(new PreguntaAdapter(ctx, R.layout.fragment_pregunta, preguntas));

        }
    }


}
