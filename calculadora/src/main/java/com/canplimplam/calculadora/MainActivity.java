package com.canplimplam.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.idResultado);

        String strNumero = textView.getText().toString();
        int numero = Integer.parseInt(strNumero);
    }

    public void botonPulsado(View view){
        int cifra = 0;
        String accion = "";

        switch(view.getId()){

            case R.id.id0:      cifra = 0;
                                break;
            case R.id.id1:      cifra = 1;
                                break;
            case R.id.id2:      cifra = 2;
                                break;
            case R.id.id3:      cifra = 3;
                                break;
            case R.id.id4:      cifra = 4;
                                break;
            case R.id.id5:      cifra = 5;
                                break;
            case R.id.id6:      cifra = 6;
                                break;
            case R.id.id7:      cifra = 7;
                                break;
            case R.id.id8:      cifra = 8;
                                break;
            case R.id.id9:      cifra = 9;
                                break;
        }

       // Toast.makeText(getApplicationContext(),"Pulsado:" + cifra, Toast.LENGTH_LONG).show();
    }
}
