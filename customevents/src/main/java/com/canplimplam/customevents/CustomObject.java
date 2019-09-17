package com.canplimplam.customevents;

import android.service.autofill.FieldClassification;

import java.util.Timer;
import java.util.TimerTask;

public class CustomObject {

    private String nombre;
 //   private CustomObject customObject;

    //Definición del listener (es una clase interna)
    public interface MyCustomObjectListener{

        public void onDataLoaded(CustomObject co);
    }

    //Variable de instancia que almacena la implementación del listener
    private MyCustomObjectListener listener;

    //Constructor
    public CustomObject(String nombre){
        this.nombre = nombre;
        this.listener = null; //Innecesario
   //     customObject = this;
        //Ponemos en marcha la tarea asincrona
        tareaAsincrona();
    }

    public CustomObject getCustomObject(){
        return this;
    }

    public String getNombre(){
        return nombre;
    }

    //Setter que permite la inyección del listener
    public void setMyCustomObjectListener(MyCustomObjectListener listener){
        this.listener = listener;
    }

    private void tareaAsincrona(){

        //Vamos a provocar la ejecución cíclica de código cad 3 segundos

        final int tiempoAleatorio = (int)(Math.random() * 3000 + 1000);
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(listener != null){

                    int numeroAleatorio = (int)(Math.random() * 1000);

                    //Disparamos
                    listener.onDataLoaded(getCustomObject());
                }
            }
        }, 0, tiempoAleatorio);
    }
}
