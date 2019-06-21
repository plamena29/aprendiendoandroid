package com.canplimplam.lecturaconstantes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.canplimplam.lecturaconstantes.model.Lectura;
import com.canplimplam.lecturaconstantes.model.LecturaServices;
import com.canplimplam.lecturaconstantes.model.LecturaServicesImpl;

import java.util.Date;

public class FormularioActivity extends AppCompatActivity {

    private LecturaServices lecturaServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        lecturaServices = LecturaServicesImpl.getInstance();
    }

    public void enviar(View view) {
        boolean error = false;
        String msgValorNoValido = "Valor no válido";
        String errorPeso = "";
        String errorDiastolica = "";
        String errorSistolica = "";

        double peso = 0;
        double diastolica = 0;
        double sistolica = 0;

        EditText editPeso = (EditText) findViewById(R.id.idPesoFormulario);
        EditText editDiastolica = (EditText) findViewById(R.id.idDiastolicaFormulario);
        EditText editSistolica = (EditText) findViewById(R.id.idSistolicaFormulario);

        if (editPeso.equals("")) {
            error = true;
            errorPeso = "Introduzca peso";
        }

        if (editDiastolica.equals("")) {
            error = true;
            errorPeso = "Introduzca Diastólica";
        }

        if (editSistolica.equals("")) {
            error = true;
            errorPeso = "Introduzca Sistólica";
        }

        if (!error) {
            peso = Double.parseDouble(editPeso.getText().toString());
            diastolica = Double.parseDouble(editDiastolica.getText().toString());
            sistolica = Double.parseDouble(editSistolica.getText().toString());
        }

        if (peso <= 0) {
            error = true;
            errorPeso = msgValorNoValido;
        }

        if (diastolica <= 0) {
            error = true;
            errorDiastolica = msgValorNoValido;
        }

        if (sistolica <= 0) {
            error = true;
            errorSistolica = msgValorNoValido;
        }

        if (diastolica <= sistolica) {
            error = true;
            errorDiastolica = msgValorNoValido;
            errorSistolica = msgValorNoValido;
        }


        if (!error) {
            //Vamos a instanciar una lectura
            Lectura lectura = new Lectura(new Date(), peso, diastolica, sistolica);

            //Vamos a persistir una lectura
            lecturaServices.create(lectura);

            //Vamos a instanciar un intent
            Intent intent = new Intent(this, MainActivity.class);

            //Vamos a cambiar de actividad

            startActivity(intent);

        } else{
            editPeso.setText(errorPeso);
            editDiastolica.setText(errorDiastolica);
            editSistolica.setText(errorSistolica);
        }


    }
}
