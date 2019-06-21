package com.canplimplam.listviewpersonalizado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class VisorImagen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_imagen);

        //El receptor de la imagen
        ImageView imagen = (ImageView) findViewById(R.id.idImagenGrande);

        //Obtenemos el intent que nos llega
        Intent intent = getIntent();

        //Los datos Extra llegan a través de un Bundle
        Bundle b = intent.getExtras();

        //Sólo si el bundle n oes null..
        if (b != null){
            imagen.setImageResource(b.getInt("IMG"));
        }
    }
}
