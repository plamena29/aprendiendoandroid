package com.canplimplam.gestormultas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.canplimplam.gestormultas.model.Multa;
import com.canplimplam.gestormultas.services.impl.MultaServicesImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Multa> multas;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        multas = MultaServicesImpl.getInstance().getAll();

        /*
        for(Multa multa: multas){
            Log.d("*******", multa.toString());
        }
        */

        lista = (ListView) findViewById(R.id.idListaMultas);

        Adaptador adaptador = new Adaptador(this);
        lista.setAdapter(adaptador);

    }
}
