package com.canplimplam.listviewpersonalizado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView listaCompra;

    private String[][] datos = {
            {"huevos", "proteína", "2 docenas", "LIDL", "10", "huevos camperos eco"},
            {"leche", "proteína", "1 pack de 6", "LIDL", "9", "leche semi calcio"},
            {"naranjas", "carbohidratos", "4 kg", "LIDL", "7", "malla de naranjas para zumo de 4kg"},
            {"platanos", "carbohidratos", "7 piezas", "LIDL", "10", "manojo de bananas"},
            {"manzanas", "carbohidratos", "10 piezas", "LIDL", "10", "manzanas golden a granel"},
            {"pan", "carbohidratos", "1 unidad", "LIDL", "9", "hogaza de pan de centeno"},
            {"nueces", "grasas", "1 paquete", "LIDL", "8", "paquete XXL"}
    };

           // "huevos", "leche", "naranjas", "platanos", "manzanas", "pan", "nueces", "chocolate negro", "chocolate Hector", "vino", "queso curado", "galletas", "pechuga de pavo", "mermelada", "kale"

    private int[] datosImg = {
            R.drawable.huevos,
            R.drawable.leche,
            R.drawable.naranjas,
            R.drawable.platanos,
            R.drawable.manzanas,
            R.drawable.pan,
            R.drawable.nueces
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaCompra = (ListView) findViewById(R.id.idListaCompra);

        listaCompra.setAdapter(new Adaptador(this, datos, datosImg));

        listaCompra.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("*****", "ENTRAMOS");

                Intent visorDetalles = new Intent(view.getContext(), VisorDetalle.class);

                visorDetalles.putExtra("alimento", datos[position][0]);
                visorDetalles.putExtra("cantidad", datos[position][2]);
                visorDetalles.putExtra("tienda", datos[position][3]);
                visorDetalles.putExtra("detalle", datos[position][5]);

                startActivity(visorDetalles);
            }
        });

    }
}
