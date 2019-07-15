package com.canplimplam.lecturaconstantes2;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.canplimplam.lecturaconstantes2.fragments.FormularioFragment;
import com.canplimplam.lecturaconstantes2.fragments.HistoricoFragment;
import com.canplimplam.lecturaconstantes2.fragments.PerfilFragment;

public class MainActivity extends AppCompatActivity implements ComunicaMenu{

    Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragments = new Fragment[3]; //creamos array de 3 eolementos

        fragments[0] = new FormularioFragment();
        fragments[1] = new HistoricoFragment();
        fragments[2] = new PerfilFragment();

        menu(0);

    }

    @Override
    public void menu(int botonPulsado) {
        FragmentManager fragmentManager = getFragmentManager(); // Ojo importarlo bien!

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // nos pide
        // 1. identificador del contenedor...
        // 2. el fragmento que queremos cargar... hay tres posibilidades.
        fragmentTransaction.replace(R.id.contenido, fragments[botonPulsado]);
        fragmentTransaction.commit();
    }
}
