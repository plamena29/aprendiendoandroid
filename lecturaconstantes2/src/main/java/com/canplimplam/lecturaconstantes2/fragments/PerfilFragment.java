package com.canplimplam.lecturaconstantes2.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.canplimplam.lecturaconstantes2.R;
import com.canplimplam.lecturaconstantes2.model.Lectura;
import com.canplimplam.lecturaconstantes2.model.Perfil;
import com.canplimplam.lecturaconstantes2.model.PerfilServices;
import com.canplimplam.lecturaconstantes2.model.PerfilServicesSQLite;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    private PerfilServices perfilServices;
    private EditText editNombre;
    private EditText editApellido;
    private EditText editEdad;
    private EditText editSexo;

    private String nombre = "";
    private String apellido = "";
    private int edad = 0;
    private String sexo = "";

    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        perfilServices = new PerfilServicesSQLite(getActivity());

        // Inflate the layout for this fragment
        final View miPerfil = inflater.inflate(R.layout.fragment_perfil, container, false);

        editNombre = (EditText) miPerfil.findViewById(R.id.idNombrePerfil);
        editApellido = (EditText) miPerfil.findViewById(R.id.idApellidoPerfil);
        editEdad = (EditText) miPerfil.findViewById(R.id.idEdadPerfil);
        editSexo = (EditText) miPerfil.findViewById(R.id.idSexoFormulario);
        Button botonActualizar = (Button) miPerfil.findViewById(R.id.idBotonPerfil);

        Perfil perfilActual = perfilServices.read();
        //setear los daots actuales

        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                nombre = editNombre.getText().toString();
                apellido = editApellido.getText().toString();
                edad = Integer.parseInt(editEdad.getText().toString());
                sexo = editSexo.getText().toString();

                Perfil perfil = new Perfil(nombre, apellido, edad, sexo);
                Log.d("******************", "perfil capturado: " + perfil.toString());
                //Vamos a persistir el perfil
                perfilServices.update(perfil);
            }
        });

        return miPerfil;
    }

}
