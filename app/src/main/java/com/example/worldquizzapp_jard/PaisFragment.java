package com.example.worldquizzapp_jard;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.worldquizzapp_jard.models.Pais;
import com.example.worldquizzapp_jard.serviceGenerator.PaisServiceGenerator;
import com.example.worldquizzapp_jard.services.PaisService;
import com.example.worldquizzapp_jard.ui.IPaisListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class PaisFragment extends Fragment {


    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private IPaisListener mListener;
    private List<Pais> listadoPais;
    private RecyclerView recyclerView;
    private MyPaisRecyclerViewAdapter adapter;
    PaisService service;
    Context ctx;


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

    private class LoadDataTask extends AsyncTask<Void, Void, List<Pais>> {

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

        @Override
        protected void onPostExecute(List<Pais> paises) {
            if (paises != null)
                recyclerView.setAdapter(new MyPaisRecyclerViewAdapter(paises, ctx, R.layout.fragment_pais));

        }
    }
}

