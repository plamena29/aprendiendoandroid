package com.canplimplam.fragmenthelloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ComunicaMenu{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void menu(int botonPulsado) {
        //Estando en main activity, lo que toca sí o sí es salir de esta actividad
        //Para ello programamos un intent para ir a Desc Activity
        Intent intent = new Intent(this, DestActivity.class);
        //A DestActivity le pasamos el numero de boton que se ha pasado
        intent.putExtra("BOTON_PULSADO", botonPulsado);
        startActivity(intent);
    }
}
