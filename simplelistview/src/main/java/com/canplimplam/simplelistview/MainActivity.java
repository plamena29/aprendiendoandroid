package com.canplimplam.simplelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private String[] valores = {"huevos", "leche", "naranjas", "platanos", "manzanas", "pan", "nueces", "chocolate negro", "chocolate Hector", "vino", "queso curado", "galletas", "pechuga de pavo", "mermelada", "kale"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.idListaCompra);

        //Adaptador
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, valores);
        listView.setAdapter(adapter);

        //Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String texto = "posisition: " + position + " Nombre: " + valores[position];
                texto += " id: " + id + " view: " + view.getClass().getSimpleName();
                texto += " parent: " + parent.getClass().getSimpleName();

                Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
