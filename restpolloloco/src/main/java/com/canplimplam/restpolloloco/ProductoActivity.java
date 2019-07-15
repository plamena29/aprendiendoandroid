package com.canplimplam.restpolloloco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.canplimplam.restpolloloco.modelo.Producto;
import com.canplimplam.restpolloloco.services.RetrofitHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import java.util.List;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductoActivity extends AppCompatActivity {

    private TextView textViewResult;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        textViewResult = (TextView) findViewById(R.id.idListaProductos);
        textViewResult.setMovementMethod(new ScrollingMovementMethod());

        jsonPlaceHolderApi = RetrofitHelper.getJsonPlaceHolderApi();

        getProductos();
    }

    private void getProductos(){

        Call<List<Producto>> call = jsonPlaceHolderApi.getProductos();

        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Producto> productos = response.body();
                for(Producto producto: productos){
                    String content = "";
                    content += "Codigo: " + producto.getCodigo() + "\n";
                    content += "Nombre: " + producto.getNombre() + "\n";
                    content += "Precio: " + producto.getPrecio() + "\n";
                    content += "Descripcion: " + producto.getDescripcion() + "\n";
                    content += "Fecha Alta: " + producto.getFechaAlta() + "\n";
                    content += "Descatalogado: " + producto.isDescatalogado() + "\n";
                    content += "Categoria: " + producto.getCategoria() + "\n\n";
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
