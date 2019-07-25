package com.canplimplam.paisesbanderas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.canplimplam.paisesbanderas.adapters.CountriesAdapter;
import com.canplimplam.paisesbanderas.modelo.Country;
import com.canplimplam.paisesbanderas.services.RetrofitHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FichaCountryActivity extends AppCompatActivity {

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    ImageView bandera;
    TextView pais;
    TextView alpha2Code;
    TextView capital;
    TextView region;
    TextView poblacion;
    TextView fronteras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_country);

        jsonPlaceHolderApi = RetrofitHelper.getJsonPlaceHolderApi();

        bandera = (ImageView) findViewById(R.id.idBanderaFicha);
        pais = (TextView) findViewById(R.id.idNameFicha);
        alpha2Code = (TextView) findViewById(R.id.idAlpha2CodeFicha);
        capital = (TextView) findViewById(R.id.idCapitalFicha);
        region = (TextView) findViewById(R.id.idRegionFicha);
        poblacion = (TextView) findViewById(R.id.idPopulationFicha);
        fronteras = (TextView) findViewById(R.id.idBordersFicha);

        Bundle extras = getIntent().getExtras();
        String idPais = extras.getString("COUNTRY_ID");

        pintarFichaPais(idPais);
    }

    private void pintarFichaPais(String idPais){
        Call<Country> call = jsonPlaceHolderApi.getById(idPais);

        call.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(FichaCountryActivity.this,"Respuesta de Servidor no satisfactoria", Toast.LENGTH_LONG);
                    return;
                }

                Country country = response.body();

                pais.setText(country.getName());
                alpha2Code.setText(country.getAlpha2Code());
                capital.setText(country.getCapital());

                String imageURL = "https://www.countryflags.io/" + country.getAlpha2Code() + "/flat/64.png";

                Picasso.get().load(imageURL).placeholder(R.drawable.banderablanca).into(bandera);
                region.setText(country.getRegion());
                poblacion.setText(String.valueOf(country.getPopulation()));

                //Tratamiento bonito de lista de fronteras
                List<String> listaFronteras = country.getBorders();
                StringBuilder sb = new StringBuilder();
                String prefix = "";
                for(String frontera: listaFronteras){
                    sb.append(prefix);
                    prefix = ", ";
                    sb.append(frontera);
                }

                fronteras.setText(sb);
            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {
                Toast.makeText(FichaCountryActivity.this,t.getMessage(), Toast.LENGTH_LONG);
            }
        });
    }
}
