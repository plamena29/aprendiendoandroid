package com.canplimplam.restpolloloco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.canplimplam.restpolloloco.modelo.LineaPedido;
import com.canplimplam.restpolloloco.modelo.Pedido;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LineaPedidoActivity extends AppCompatActivity {

    private TextView textViewResult;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linea_pedido);

        textViewResult = (TextView) findViewById(R.id.idSummaryLineasPedido);
        textViewResult.setMovementMethod(new ScrollingMovementMethod());

        // Creates the json object which will manage the information received
        GsonBuilder builder = new GsonBuilder();

        // Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                long millisecons = json.getAsJsonPrimitive().getAsLong();
                return new Date(millisecons);
            }
        });

        Gson gson = builder.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pedi-gest.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getLineasPedido();
    }

    public void getLineasPedido(){

        Call<List<LineaPedido>> call = jsonPlaceHolderApi.getLineasPedido();

        call.enqueue(new Callback<List<LineaPedido>>() {
            @Override
            public void onResponse(Call<List<LineaPedido>> call, Response<List<LineaPedido>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                Map<String,Integer> summaryListaPedidos = new HashMap<>();
                List<LineaPedido> lineasPedido = response.body();

                for(LineaPedido lineaPedido: lineasPedido){
                    String categoriaLineaPedido = lineaPedido.getProducto().getCategoria();
                    Integer cantidadLineaPedido = lineaPedido.getCantidad();

                    Set<String> llaves = summaryListaPedidos.keySet();
                    if(llaves.contains(categoriaLineaPedido)){
                        cantidadLineaPedido += summaryListaPedidos.get(categoriaLineaPedido);
                    }
                    summaryListaPedidos.put(categoriaLineaPedido, cantidadLineaPedido);
                }

                String content = "CATEGORIA" + "\t\t\t" + "CANTIDAD" + "\n";
                      content += "-------------------" + "\t\t\t" + "-----------------" + "\n";
                Set<String> llaves = summaryListaPedidos.keySet();
                Integer total = 0;
                for(String llave: llaves){
                    total += summaryListaPedidos.get(llave);
                    content += llave;
                    for(int i = 0; i < (30 - llave.length()); i++){
                        content += " ";
                    }
                    content += summaryListaPedidos.get(llave) + "\n";
                }
                content += "------------------" + "\t\t\t" + "----------------" + "\n";
                content += "TOTAL:" + "\t\t\t\t\t\t" + total;
                textViewResult.append(content);
            }

            @Override
            public void onFailure(Call<List<LineaPedido>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
