package com.example.worldquizzapp_jard.ui.ranking;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldquizzapp_jard.R;
import com.example.worldquizzapp_jard.models.Usuario;
import com.example.worldquizzapp_jard.rankingAdapter.IUsuarioRankingListener;
import com.example.worldquizzapp_jard.rankingAdapter.MyUsuarioRankingRecyclerViewAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class RankingFragment extends Fragment{


    // Usuario usuario;
    Context ctx;
    RecyclerView recyclerView;
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private List<Usuario> usuariosList = new ArrayList<>();
    private MyUsuarioRankingRecyclerViewAdapter adapter;
    private RankingViewModel rankingViewModel;
    private IUsuarioRankingListener mListener;
    private FirebaseFirestore db;
    private Long l;


    Comparator<Usuario> TOTAL_COMPARATOR = new Comparator<Usuario>() {
        @Override
        public int compare(Usuario u1, Usuario u2) {
            return Long.compare(u2.getTotal(), u1.getTotal());
        }
    };

    Comparator<Usuario> PPP_COMPARATOR = new Comparator<Usuario>() {
        @Override
        public int compare(Usuario u1, Usuario u2) {
            if (u2.getResultados().size() == 0) {
                if (u1.getResultados().size() == 0) {
                    return Double.compare(0, 0);
                } else {
                    return Double.compare(0, u1.getTotal()/u1.getResultados().size());
                }
            } else if (u1.getResultados().size() == 0) {
                return Double.compare(u2.getTotal()/u2.getResultados().size(), 0);
            }
            return Double.compare((u2.getTotal()/u2.getResultados().size()), (u1.getTotal()/u1.getResultados().size()));
        }
    };


    public RankingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        db = FirebaseFirestore.getInstance();
        rankingViewModel =
                ViewModelProviders.of(this).get(RankingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_usuario_ranking_list, container, false);

        if (root instanceof RecyclerView) {
            Context context = root.getContext();
            recyclerView = (RecyclerView) root;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }


            adapter = new MyUsuarioRankingRecyclerViewAdapter(
                    ctx,
                    R.layout.fragment_ranking,
                    usuariosList
            );


            recyclerView.setAdapter(adapter);

            db.collection("usuarios")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            int i=0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                usuariosList.add(new Usuario(document.getData().get("nombre").toString(),document.getData().get("email").toString(), (List<Long>) document.getData().get("resultados"),document.getData().get("avatar").toString()));
                                i++;
                            }
                            adapter.notifyDataSetChanged();
                    Collections.sort(usuariosList,PPP_COMPARATOR);
                        }

                    });

        }

            return root;
        }
    @Override
    public void onAttach(Context context) {
        ctx =context;
        super.onAttach(context);
        if (context instanceof IUsuarioRankingListener) {
            mListener = (IUsuarioRankingListener) context;
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

    public class CargarUsuarios extends AsyncTask<Void, Void, List<Usuario>>{



        @Override
        protected List<Usuario> doInBackground(Void... voids) {




            return usuariosList;
        }

        @Override
        protected void onPostExecute(List<Usuario> usuarioList) {




        }
    }
}
