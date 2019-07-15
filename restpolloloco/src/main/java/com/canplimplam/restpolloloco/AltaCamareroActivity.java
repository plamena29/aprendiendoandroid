package com.canplimplam.restpolloloco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.canplimplam.restpolloloco.modelo.Camarero;
import com.canplimplam.restpolloloco.services.RetrofitHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AltaCamareroActivity extends AppCompatActivity{

    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private Button botonCrear;
    private TextView editCodigoCamarero;
    private TextView editNombreCamarero;
    private int codigoCamareroInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_camarero);
        botonCrear = (Button) findViewById(R.id.idBotonCrearCamarero);
        editCodigoCamarero = (TextView) findViewById(R.id.idCodigoCamareroFormulario);
        editNombreCamarero = (TextView) findViewById(R.id.idNombreCamareroFormulario);
        codigoCamareroInt = (int)(Math.random()*1000000);
        editCodigoCamarero.setText(String.valueOf(codigoCamareroInt));

        jsonPlaceHolderApi = RetrofitHelper.getJsonPlaceHolderApi();

        botonCrear.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final View vista = v;
                String nombre = editNombreCamarero.getText().toString();
                int codigo = (int) Integer.parseInt(editCodigoCamarero.getText().toString());
                Camarero nuevoCamarero = new Camarero(codigo, nombre);

                Call<Camarero> call = jsonPlaceHolderApi.create(nuevoCamarero);

                call.enqueue(new Callback<Camarero>() {
                    @Override
                    public void onResponse(Call<Camarero> call, Response<Camarero> response) {
                        if(!response.isSuccessful()){
                            //resultadoAltaCamarero.setText("Code: " + response.code());
                            return;
                        }
                        Camarero camareroCreado = response.body();
                        String content = "";
                        content += "Codigo: " + camareroCreado.getCodigo() + "\n";
                        content += "Nombre: " + camareroCreado.getNombre() + "\n\n";
                        Log.d("*********", content);

                        Intent intent = new Intent(vista.getContext(), CamareroActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Camarero> call, Throwable t) {
                        //resultadoAltaCamarero.setText(t.getMessage());
                    }
                });
            }
        });

    }
}
