package com.canplimplam.restpolloloco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.canplimplam.restpolloloco.modelo.Producto;
import com.canplimplam.restpolloloco.services.RetrofitHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AltaProductoActivity extends AppCompatActivity {

    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private Button botonCrear;
    private TextView editCodigoProducto;
    private int codigoProductoInt;
    private TextView editNombreProducto;
    private TextView editPrecio;
    private double precioDouble;
    private TextView editDescripcion;
    private TextView editCategoria;

  //  private TextView resultadoAltaProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_producto);

        botonCrear = (Button) findViewById(R.id.idBotonCrearProducto);
        editCodigoProducto = (TextView) findViewById(R.id.idCodigoProductoFormulario);
        editNombreProducto = (TextView) findViewById(R.id.idNombreProductoFormulario);
        editPrecio = (TextView) findViewById(R.id.idPrecioProductoFormulario);
        editDescripcion = (TextView) findViewById(R.id.idDescripcionProductoFormulario);
        editCategoria = (TextView) findViewById(R.id.idCategoriaProductoFormulario);
        codigoProductoInt = (int)(Math.random()*1000000);
        editCodigoProducto.setText(String.valueOf(codigoProductoInt));

        jsonPlaceHolderApi = RetrofitHelper.getJsonPlaceHolderApi();

        botonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View vista = v;

                int codigo = (int) Integer.parseInt(editCodigoProducto.getText().toString());
                String nombre = editNombreProducto.getText().toString();
                double precio = Double.parseDouble(editPrecio.getText().toString());
                String descripcion = editDescripcion.getText().toString();
                Date fechaAlta = new Date();
                boolean descatalogado = false;
                String categoria = editCategoria.getText().toString();

                Producto nuevoProducto = new Producto(codigo, nombre, precio, descripcion, fechaAlta, descatalogado, categoria);

                Call<Producto> call = jsonPlaceHolderApi.create(nuevoProducto);

                call.enqueue(new Callback<Producto>() {
                    @Override
                    public void onResponse(Call<Producto> call, Response<Producto> response) {
                        if(!response.isSuccessful()){
                         //   resultadoAltaProducto.setText("Code: " + response.code());
                            return;
                        }
                        Producto productoCreado = response.body();
                        String content = "";
                        content += "Codigo: " + productoCreado.getCodigo() + "\n";
                        content += "Nombre: " + productoCreado.getNombre() + "\n\n";
                        Log.d("*********", content);

                        Intent intent = new Intent(vista.getContext(), ProductoActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Producto> call, Throwable t) {
                      //  resultadoAltaProducto.setText(t.getMessage());
                    }
                });
            }
        });
    }
}
