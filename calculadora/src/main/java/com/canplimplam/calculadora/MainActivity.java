package com.canplimplam.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // Variables de instancia!!!!
    // Instancia = objeto = calculadora
    TextView textView;
    private Estado estado = Estado.INICIAL;
    private Accion accion = Accion.NINGUNA;
    private String coma = "";
    private double operando1 = 0.0;
    private double operando2 = 0.0;
    private double resultado = 0.0;
    private int pEntera = 0;
    private int pDecimal = 0;
    private boolean decimal = false;
    private int contadorDecimal = 0;
    private int contadorEntera = 0;
    private String error ="";
    private int longitudDisplay = 0;
    String padding0 = "";

    DecimalFormat df = new DecimalFormat("#.########");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.idResultado);
        textView.setText("0.0");

    }

    public void botonPulsado(View view){
        int cifra = 0;

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
            case R.id.idCE:     cifra = -1;
                                accion = Accion.BORRAR;
                                break;
            case R.id.idMas:    cifra = -1;
                                accion = Accion.SUMAR;
                                break;
            case R.id.idMenos:  cifra = -1;
                                accion = Accion.RESTAR;
                                break;
            case R.id.idPor:    cifra = -1;
                                accion = Accion.MULTIPLICAR;
                                break;
            case R.id.idDividir:  cifra = -1;
                                accion = Accion.DIVIDIR;
                                break;
            case R.id.idDecimal:  cifra = -1;
                                coma = "coma";
                                decimal = true;
                                break;
            case R.id.idIgual:  cifra = -1;
                                if (estado == Estado.OPERANDO2){
                                    estado = Estado.RESULTADO;
                                    }
                                break;
        }

        if ((cifra != -1 || coma.equals("coma"))&& (estado == Estado.INICIAL || estado == Estado.OPERANDO1)){
            estado = Estado.OPERANDO1;
            if(cifra != -1 && decimal == false){
                contadorEntera += 1;
                pEntera = pEntera * 10 + cifra;
            } else if (cifra != -1 && decimal == true){
                contadorDecimal += 1;
                pDecimal = pDecimal * 10 + cifra;
                if ((cifra == 0) && (pDecimal == 0)){
                    padding0 += "0";
                }
            }

            longitudDisplay = contadorEntera + contadorDecimal;
            if (coma.equals("coma")){
                longitudDisplay += 1;
            }
            if (longitudDisplay > 9){
                error = "ERROR";
            }
            operando1 = Double.parseDouble(pEntera + "." + padding0 + pDecimal);
            Log.d("INFO", "Operando1:" + operando1);
            StringBuilder sb = new StringBuilder();

            if (error.equals("ERROR")){
                sb.append(error);
            }else{
            sb.append(pEntera);
            if(decimal == true){
                sb.append(".");
                if (contadorDecimal != 0){
                    sb.append(padding0);
                }
                if (pDecimal != 0){
                    sb.append(pDecimal);
                }
            }
            }
            textView.setText(sb.toString());

        } else if((cifra != -1 || coma.equals("coma"))&& estado == Estado.OPERANDO2) {
            if(cifra != -1 && decimal == false){
                contadorEntera += 1;
                pEntera = pEntera * 10 + cifra;
            } else if (cifra != -1 && decimal == true){
                contadorDecimal += 1;
                pDecimal = pDecimal * 10 + cifra;
                if ((cifra == 0) && (pDecimal == 0)){
                    padding0 += "0";
                }
            }

            longitudDisplay = contadorEntera + contadorDecimal;
            if (coma.equals("coma")){
                longitudDisplay += 1;
            }
            if (longitudDisplay > 9){
                error = "ERROR";
            }

            operando2 = Double.parseDouble(pEntera + "." + padding0 + pDecimal);
            Log.d("INFO", "Operando2:" + operando2);
            StringBuilder sb = new StringBuilder();

            if (error.equals("ERROR")){
                sb.append(error);
            } else {
                sb.append(pEntera);
                if (decimal == true) {
                    sb.append(".");
                    if (contadorDecimal != 0){
                        sb.append(padding0);
                    }
                    if (pDecimal != 0){
                        sb.append(pDecimal);
                    }
                }
            }
            textView.setText(sb.toString());
        }
        if(estado == Estado.OPERANDO1 && (accion == Accion.SUMAR || accion == Accion.RESTAR || accion == Accion.MULTIPLICAR || accion == Accion.DIVIDIR)){
            estado = Estado.OPERANDO2;
            contadorDecimal = 0;
            pEntera = 0;
            pDecimal = 0;
            decimal = false;
            coma = "";
            error = "";
            padding0 = "";
        }
        if(estado == Estado.RESULTADO){
            switch(accion){
                case SUMAR: resultado = operando1 + operando2;
                    break;
                case RESTAR: resultado = operando1 - operando2;
                    break;
                case MULTIPLICAR: resultado = operando1 * operando2;
                    break;
                case DIVIDIR: if (operando2 == 0) {
                    error = "ERROR";
                    } else {
                    resultado = operando1 / operando2;
                    }
                    break;
            }

            Log.d("INFO", "Resultado:" + resultado);
            StringBuilder sb = new StringBuilder();
            if (error.equals("ERROR")){
                sb.append(error);
            } else {
                sb.append(df.format(resultado));
            }
            textView.setText(sb.toString());
        }
        if(accion == Accion.BORRAR){
            estado = Estado.INICIAL;
            accion = Accion.NINGUNA;
            decimal = false;
            operando1 = 0.0;
            operando2 = 0.0;
            resultado = 0.0;
            contadorDecimal = 0;
            contadorEntera = 0;
            pEntera = 0;
            pDecimal = 0;
            coma = "";
            error = "";
            padding0 = "";
            textView.setText("0.0");
        }

       Toast.makeText(getApplicationContext(),"Pulsado:" + cifra, Toast.LENGTH_LONG).show();
    }

    enum Estado{
        INICIAL, OPERANDO1, OPERANDO2, RESULTADO;
    }
    enum Accion{
        NINGUNA, SUMAR, RESTAR, MULTIPLICAR, DIVIDIR, BORRAR;
    }
}
