package com.canplimplam.listviewpersonalizado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class VisorDetalle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("*****","ENTRAMOS EN VISOR....");

        setContentView(R.layout.activity_visor_detalle);



        //El receptor de la imagen
        TextView alimento = (TextView) findViewById(R.id.idTituloDetalle);
        TextView cantidad = (TextView) findViewById(R.id.idCantidadDetalle);
        TextView tienda = (TextView) findViewById(R.id.idTiendaDetalle);
        TextView detalle = (TextView) findViewById(R.id.idDetalleProducto);

        //Obtenemos el intent que nos llega
        Intent intent = getIntent();

        //Los datos Extra llegan a través de un Bundle
        Bundle b = intent.getExtras();

        //Sólo si el bundle n oes null..
        if (b != null){
            alimento.setText(b.getString("alimento"));
            cantidad.setText(b.getString("cantidad"));
            tienda.setText(b.getString("tienda"));
            detalle.setText(b.getString("detalle"));
        }
    }
}
