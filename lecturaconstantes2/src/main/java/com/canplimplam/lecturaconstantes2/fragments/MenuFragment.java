package com.canplimplam.lecturaconstantes2.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.canplimplam.lecturaconstantes2.ComunicaMenu;
import com.canplimplam.lecturaconstantes2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {

    // referencias a los 3 botones
    private final int[] BOTONES_MENU = {R.id.boton1,
                                         R.id.boton2,
                                           R.id.boton3};

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View miMenu = inflater.inflate(R.layout.fragment_menu, container, false);

        //Declaro una variable de tipo ImageButton
        //Vamos a iterar los botones y para cada uno añadiremos un listener
        Button botonMenu;

        //para cada botón..
        for(int i = 0; i < BOTONES_MENU.length; i++){
            botonMenu = (Button) miMenu.findViewById(BOTONES_MENU[i]);
            final int BOTON_i = i;

            //Añadimos listener
            botonMenu.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    //Hemos de saber donde estamos
                    Activity actividadActual = getActivity();
                    ComunicaMenu cm = (ComunicaMenu) actividadActual;
                    cm.menu(BOTON_i);
                    //((ComunicaMenu) actividadActual).menu(boton);
                }
            });
        }

        return miMenu;
    }

}
