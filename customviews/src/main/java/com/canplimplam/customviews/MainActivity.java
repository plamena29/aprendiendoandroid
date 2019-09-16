package com.canplimplam.customviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Crear datos hardcode
        List<Registro> registros = new ArrayList<>();

        registros.add(new Registro("Barcelona", 29));
        registros.add(new Registro("Madrid", 31));
        registros.add(new Registro("Valencia", 29));
        registros.add(new Registro("Sevilla", 33));
        registros.add(new Registro("Oviedo", 25));
        registros.add(new Registro("Salamanca", 21));
        registros.add(new Registro("Bilbao", 12));

        ListViewAdapter adaptador = new ListViewAdapter(this, registros);
        ListView listView = (ListView) findViewById(R.id.idListView);
        listView.setAdapter(adaptador);

    }
}
