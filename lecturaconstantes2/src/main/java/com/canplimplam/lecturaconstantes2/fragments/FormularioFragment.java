package com.canplimplam.lecturaconstantes2.fragments;


import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.canplimplam.lecturaconstantes2.ComunicaMenu;
import com.canplimplam.lecturaconstantes2.MainActivity;
import com.canplimplam.lecturaconstantes2.R;
import com.canplimplam.lecturaconstantes2.model.Lectura;
import com.canplimplam.lecturaconstantes2.model.LecturaServices;
import com.canplimplam.lecturaconstantes2.model.LecturaServicesSQLite;
import com.canplimplam.lecturaconstantes2.services.JsonPlaceHolderApi;
import com.canplimplam.lecturaconstantes2.services.RetrofitHelper;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FormularioFragment extends Fragment {

    private EditText editPeso;
    private EditText editDiastolica;
    private EditText editSistolica;
    private double peso = 0;
    private double diastolica = 0;
    private double sistolica = 0;
    private double longitud = 0;
    private double latitud = 0;
    private LecturaServices lecturaServices;

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private LocationManager locationManager;
    private String providerName;

    public FormularioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        lecturaServices = new LecturaServicesSQLite(getActivity());
        // Inflate the layout for this fragment

        final View miFormulario = inflater.inflate(R.layout.fragment_formulario, container, false);

        editPeso = (EditText) miFormulario.findViewById(R.id.idPesoFormulario);
        editDiastolica = (EditText) miFormulario.findViewById(R.id.idDiastolicaFormulario);
        editSistolica = (EditText) miFormulario.findViewById(R.id.idSistolicaFormulario);
        Button botonEnviar = (Button) miFormulario.findViewById(R.id.button);

        jsonPlaceHolderApi = RetrofitHelper.getJsonPlaceHolderApi();

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        providerName = locationManager.getBestProvider(criteria, false);
        Log.d("**", "providerName: " + providerName);
        botonEnviar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Coordenadas
                if (providerName != null && !providerName.equals("")) {
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        Log.d("**", "no hay permisos");
                        return;
                    }
                    Location location = locationManager.getLastKnownLocation(providerName);
                    if(location != null){
                        longitud = location.getLongitude();
                        latitud = location.getLatitude();
                        Log.d("**", "longitud: " + longitud);
                        Log.d("**", "latitud: " + latitud);
                    }
                    else{
                        Toast.makeText(getActivity(),"No se ha encontrado proveedor",Toast.LENGTH_SHORT);
                    }

                }

                peso = Double.parseDouble(editPeso.getText().toString());
                diastolica = Double.parseDouble(editDiastolica.getText().toString());
                sistolica = Double.parseDouble(editSistolica.getText().toString());
                Lectura lectura = new Lectura(new Date(), peso, diastolica, sistolica, longitud, latitud);

                //Vamos a persistir una lectura
                //lecturaServices.create(lectura);
                Call<Lectura> call = jsonPlaceHolderApi.create(9,lectura);

                call.enqueue(new Callback<Lectura>() {
                    @Override
                    public void onResponse(Call<Lectura> call, Response<Lectura> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(getActivity(),"Respuesta de servidor no satisfactoria",Toast.LENGTH_SHORT);
                            return;
                        }
                        Lectura lecturaCreada = response.body();

                        ComunicaMenu cm = (ComunicaMenu) getActivity();
                        cm.menu(1);
                    }

                    @Override
                    public void onFailure(Call<Lectura> call, Throwable t) {
                        Toast.makeText(getActivity(),"Fallo de conexión",Toast.LENGTH_SHORT);
                    }
                });
            }
        });
        return miFormulario;
    }
}
