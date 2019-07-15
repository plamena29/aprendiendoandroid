package com.canplimplam.restpolloloco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.canplimplam.restpolloloco.modelo.Camarero;
import com.canplimplam.restpolloloco.services.RetrofitHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CamareroActivity extends AppCompatActivity {

    private TextView textViewResult;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camarero);

        textViewResult = (TextView) findViewById(R.id.idListaCamareros);
        textViewResult.setMovementMethod(new ScrollingMovementMethod());

        jsonPlaceHolderApi = RetrofitHelper.getJsonPlaceHolderApi();

        getCamareros();
    }

    private void getCamareros(){

        Call<List<Camarero>> call = jsonPlaceHolderApi.getCamareros();

        call.enqueue(new Callback<List<Camarero>>() {
            @Override
            public void onResponse(Call<List<Camarero>> call, Response<List<Camarero>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Camarero> camareros = response.body();
                for(Camarero camarero: camareros){
                    String content = "";
                    content += "Codigo: " + camarero.getCodigo() + "\n";
                    content += "Nombre: " + camarero.getNombre() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Camarero>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
