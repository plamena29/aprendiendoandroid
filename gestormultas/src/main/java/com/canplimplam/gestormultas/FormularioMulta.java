package com.canplimplam.gestormultas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.canplimplam.gestormultas.model.Agente;
import com.canplimplam.gestormultas.model.Multa;
import com.canplimplam.gestormultas.model.Tipo;
import com.canplimplam.gestormultas.services.AgenteServices;
import com.canplimplam.gestormultas.services.MultaServices;
import com.canplimplam.gestormultas.services.impl.AgenteServicesImpl;
import com.canplimplam.gestormultas.services.impl.MultaServicesImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormularioMulta extends AppCompatActivity {

    private MultaServices multaServices;
    private AgenteServices agenteServices;
    private AdaptadorAgente adaptadorAgente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_multa);
        multaServices = MultaServicesImpl.getInstance();
        agenteServices = AgenteServicesImpl.getInstance();
    }

    public void crearmulta(View view) {

        //Instanciamos multa nueva
        final Multa multa = new Multa();

        //Tratamos fecha
        SimpleDateFormat sdfFechaCompleta = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String strFechaCompleta = "";
        Date fechaCompleta = new Date();

        EditText editDia = (EditText) findViewById(R.id.idDiaFormulario);
        EditText editHora = (EditText) findViewById(R.id.idHoraFormulario);
        strFechaCompleta = editDia.getText().toString() + " " + editHora.getText().toString();

        try {
            fechaCompleta = sdfFechaCompleta.parse(strFechaCompleta);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Tratamos agente
        Spinner spinnerAgentes = (Spinner) findViewById(R.id.idAgenteFormulario);
        adaptadorAgente = new AdaptadorAgente(this, agenteServices.getAll());
        spinnerAgentes.setAdapter(adaptadorAgente);
        spinnerAgentes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Agente clickedAgente = (Agente) parent.getItemAtPosition(position);
                multa.setAgente(clickedAgente);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
       // Agente agente = agenteServices.read(100L);


        multa.setCodigo(null);
        multa.setFechaHora(fechaCompleta);


        multa.setMotivo("Test motivo...");
        multa.setAceptada(true);
        multa.setObservaciones("Test observaciones...");
        multa.setImporte(120.0);
        multa.setTipo(Tipo.LEVE);

        multaServices.create(multa);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
