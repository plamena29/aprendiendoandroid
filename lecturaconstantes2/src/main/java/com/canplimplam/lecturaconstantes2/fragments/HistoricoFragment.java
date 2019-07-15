package com.canplimplam.lecturaconstantes2.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.canplimplam.lecturaconstantes2.Adaptador;
import com.canplimplam.lecturaconstantes2.R;
import com.canplimplam.lecturaconstantes2.model.Lectura;
import com.canplimplam.lecturaconstantes2.services.JsonPlaceHolderApi;
import com.canplimplam.lecturaconstantes2.services.RetrofitHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoricoFragment extends Fragment {

    private ListView lista;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    public HistoricoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View miHistorico = inflater.inflate(R.layout.fragment_historico, container, false);
        jsonPlaceHolderApi = RetrofitHelper.getJsonPlaceHolderApi();
        lista = (ListView) miHistorico.findViewById(R.id.idLecturas);

        pintarRegistros();

        return miHistorico;
    }

//Código para cuando la View ya está creada
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void pintarRegistros(){
        Call<List<Lectura>> call = jsonPlaceHolderApi.getLecturas(9);

        call.enqueue(new Callback<List<Lectura>>() {
            @Override
            public void onResponse(Call<List<Lectura>> call, Response<List<Lectura>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(),"Respuesta de Servidor no satisfactoria", Toast.LENGTH_LONG);
                    return;
                }

                List<Lectura> lecturas = response.body();

                Adaptador adaptador = new Adaptador(getActivity(), lecturas);
                lista.setAdapter(adaptador);
            }

            @Override
            public void onFailure(Call<List<Lectura>> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(), Toast.LENGTH_LONG);
            }
        });
    }
}
