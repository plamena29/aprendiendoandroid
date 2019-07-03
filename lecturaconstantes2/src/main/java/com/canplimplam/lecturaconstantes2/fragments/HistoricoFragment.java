package com.canplimplam.lecturaconstantes2.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.canplimplam.lecturaconstantes2.Adaptador;
import com.canplimplam.lecturaconstantes2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoricoFragment extends Fragment {

    private ListView lista;

    public HistoricoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("*******", "OnCreateView()");
        // Inflate the layout for this fragment
        View miHistorico = inflater.inflate(R.layout.fragment_historico, container, false);

        lista = (ListView) miHistorico.findViewById(R.id.idLecturas);

        Adaptador adaptador = new Adaptador(getActivity());

        lista.setAdapter(adaptador);

        return miHistorico;
    }

//Código para cuando la View ya está creada
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("*******", "OnViewCreated()");
    }
}
