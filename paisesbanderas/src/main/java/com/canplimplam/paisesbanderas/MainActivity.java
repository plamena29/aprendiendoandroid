package com.canplimplam.paisesbanderas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.canplimplam.paisesbanderas.adapters.CountriesAdapter;
import com.canplimplam.paisesbanderas.modelo.Country;
import com.canplimplam.paisesbanderas.services.RetrofitHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView listaResultado;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaResultado = (ListView) findViewById(R.id.idListaPaises);
        jsonPlaceHolderApi = RetrofitHelper.getJsonPlaceHolderApi();

        pintarPaises();

        listaResultado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Country country = (Country) listaResultado.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, FichaCountryActivity.class);
                intent.putExtra("COUNTRY_ID", country.getAlpha2Code());
                startActivity(intent);
            }
        });
    }

    private void pintarPaises(){

        Call<List<Country>> call = jsonPlaceHolderApi.getAll();

        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Respuesta de Servidor no satisfactoria", Toast.LENGTH_LONG);
                    return;
                }

                List<Country> paises = response.body();
                CountriesAdapter adaptador = new CountriesAdapter(MainActivity.this, paises);
                listaResultado.setAdapter(adaptador);
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(), Toast.LENGTH_LONG);
            }
        });
    }
}
