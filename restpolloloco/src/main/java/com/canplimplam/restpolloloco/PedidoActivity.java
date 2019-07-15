package com.canplimplam.restpolloloco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.canplimplam.restpolloloco.modelo.LineaPedido;
import com.canplimplam.restpolloloco.modelo.Pedido;
import com.canplimplam.restpolloloco.modelo.Producto;
import com.canplimplam.restpolloloco.services.RetrofitHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PedidoActivity extends AppCompatActivity {

    private TextView textViewResult;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
 //   private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        textViewResult = (TextView) findViewById(R.id.idListaPedidos);
        textViewResult.setMovementMethod(new ScrollingMovementMethod());

        jsonPlaceHolderApi = RetrofitHelper.getJsonPlaceHolderApi();

        getPedidos();
    }

    private void getPedidos(){
        Call<List<Pedido>> call = jsonPlaceHolderApi.getPedidos();

        call.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Pedido> pedidos = response.body();
                for(Pedido pedido: pedidos){
                    String content = "";
                    content += "Id: " + pedido.getId() + "\n";
                    content += "Fecha: " + pedido.getFecha() + "\n";
                    content += "Mesa: " + pedido.getMesa() + "\n";
                    content += "Camarero: " + pedido.getCamarero().getNombre() + "\n";

                    List<LineaPedido> lineasPedido = pedido.getLineasPedido();
                    for(LineaPedido lineaPedido: lineasPedido){
                        content += "\t" + lineaPedido.getCantidad();
                        content += "\t" + lineaPedido.getProducto().getNombre();
                        content += "\t" + lineaPedido.getPrecio() + "\n";
                    }
                    content += "\n";
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
