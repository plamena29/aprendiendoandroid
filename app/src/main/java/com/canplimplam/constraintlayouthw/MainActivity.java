package com.canplimplam.constraintlayouthw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.security.Timestamp;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Date timestampOnPause = new Date();
    private Date timestampOnResume = new Date();
    private long milisegundos = 0;
    private TextView displaySegundosEnPausa;

    static{
        Log.d("INFO", "SE INICIALIZA EL MUNDO ESTATICO");
    }

    public MainActivity(){
        Log.d("INFO", "DENTRO DE METODO CONSTRUCTOR");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("INFO", "DENTRO DE onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displaySegundosEnPausa = (TextView) findViewById(R.id.segundosenpausa);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("INFO", "ON PAUSE");
        timestampOnPause.setTime(System.currentTimeMillis());
        Log.d("INFO", timestampOnPause.toString());
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("INFO", "ON RESUME");
        timestampOnResume.setTime(System.currentTimeMillis());
        Log.d("INFO", timestampOnResume.toString());
        milisegundos = timestampOnResume.getTime() - timestampOnPause.getTime();
        Log.d("INFO", "Milisegundos en pausa:" + String.valueOf( milisegundos));
        StringBuilder sb = new StringBuilder();
        sb.append(milisegundos/1000).append(" segundos en Pausa");
        displaySegundosEnPausa.setText(sb.toString());
    }
}
