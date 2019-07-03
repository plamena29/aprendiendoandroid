package com.canplimplam.lecturaconstantes2.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.canplimplam.lecturaconstantes2.MainActivity;
import com.canplimplam.lecturaconstantes2.R;
import com.canplimplam.lecturaconstantes2.model.Lectura;
import com.canplimplam.lecturaconstantes2.model.LecturaServices;
import com.canplimplam.lecturaconstantes2.model.LecturaServicesSQLite;

import java.util.Date;

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
    private LecturaServices lecturaServices;

    public FormularioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        lecturaServices = new LecturaServicesSQLite(getActivity());
        // Inflate the layout for this fragment

        final View miFormulario = inflater.inflate(R.layout.fragment_formulario, container, false);

        Button botonEnviar = (Button) miFormulario.findViewById(R.id.button);

        botonEnviar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                editPeso = (EditText) miFormulario.findViewById(R.id.idPesoFormulario);
                editDiastolica = (EditText) miFormulario.findViewById(R.id.idDiastolicaFormulario);
                editSistolica = (EditText) miFormulario.findViewById(R.id.idSistolicaFormulario);
                peso = Double.parseDouble(editPeso.getText().toString());
                diastolica = Double.parseDouble(editDiastolica.getText().toString());
                sistolica = Double.parseDouble(editSistolica.getText().toString());
                Lectura lectura = new Lectura(new Date(), peso, diastolica, sistolica);
                Log.d("******************", "lectura capturada: " + lectura.toString());
                //Vamos a persistir una lectura
                lecturaServices.create(lectura);
            }
        });
        return miFormulario;
    }

    public void enviar(View view){
        Log.d("*********", "enviar");
        boolean error = false;
        String msgValorNoValido = "Valor no válido";
        String errorPeso = "";
        String errorDiastolica = "";
        String errorSistolica = "";

        double peso = 0;
        double diastolica = 0;
        double sistolica = 0;

        EditText editPeso = (EditText) view.findViewById(R.id.idPesoFormulario);
        EditText editDiastolica = (EditText) view.findViewById(R.id.idDiastolicaFormulario);
        EditText editSistolica = (EditText) view.findViewById(R.id.idSistolicaFormulario);
        Log.d("*********", "identificamos campos");

        if (editPeso.equals("")) {
            error = true;
            errorPeso = "Introduzca peso";
        }

        if (editDiastolica.equals("")) {
            error = true;
            errorPeso = "Introduzca Diastólica";
        }

        if (editSistolica.equals("")) {
            error = true;
            errorPeso = "Introduzca Sistólica";
        }

        if (!error) {
            peso = Double.parseDouble(editPeso.getText().toString());
            diastolica = Double.parseDouble(editDiastolica.getText().toString());
            sistolica = Double.parseDouble(editSistolica.getText().toString());
            Log.d("*********", "no error");
        }

        if (peso <= 0) {
            error = true;
            errorPeso = msgValorNoValido;
        }

        if (diastolica <= 0) {
            error = true;
            errorDiastolica = msgValorNoValido;
        }

        if (sistolica <= 0) {
            error = true;
            errorSistolica = msgValorNoValido;
        }

        if (diastolica <= sistolica) {
            error = true;
            errorDiastolica = msgValorNoValido;
            errorSistolica = msgValorNoValido;
        }


        if (!error) {
            //Vamos a instanciar una lectura
            Log.d("******************", "capturando lectura: " );
            Lectura lectura = new Lectura(new Date(), peso, diastolica, sistolica);
            Log.d("******************", "lectura capturada: " + lectura.toString());
            //Vamos a persistir una lectura
            lecturaServices.create(lectura);

        } else{
            editPeso.setText(errorPeso);
            editDiastolica.setText(errorDiastolica);
            editSistolica.setText(errorSistolica);
        }

    }
}
