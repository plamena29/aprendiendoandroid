package com.canplimplam.restpolloloco;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.canplimplam.restpolloloco.adaptadores.CamareroListAdapter;
import com.canplimplam.restpolloloco.adaptadores.PedidoListAdapter;
import com.canplimplam.restpolloloco.adaptadores.ProductoListAdapter;
import com.canplimplam.restpolloloco.modelo.Camarero;
import com.canplimplam.restpolloloco.modelo.Pedido;
import com.canplimplam.restpolloloco.modelo.Producto;
import com.canplimplam.restpolloloco.services.RetrofitHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListadoActivity extends AppCompatActivity {

    private ListView listaResultado;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        listaResultado = (ListView) findViewById(R.id.idListaResultado);
        jsonPlaceHolderApi = RetrofitHelper.getJsonPlaceHolderApi();

        Bundle extras = getIntent().getExtras();
        String solicitud = extras.getString("CONTENIDO_LISTADO");

        switch (solicitud){
            case "CAMAREROS":
                pintarCamareros();
                break;
            case "PRODUCTOS":
                pintarProductos();
                listaResultado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Producto producto = (Producto) listaResultado.getItemAtPosition(position);
                        Intent intent = new Intent(ListadoActivity.this, FichaProductoActivity.class);
                        intent.putExtra("PRODUCTO_DETALLE", producto);
                        startActivity(intent);
                    }
                });
                break;
            case "PEDIDOS":
                pintarPedidos();
                break;
        }
    }

    private void pintarCamareros(){

        Call<List<Camarero>> call = jsonPlaceHolderApi.getCamareros();

        call.enqueue(new Callback<List<Camarero>>() {
            @Override
            public void onResponse(Call<List<Camarero>> call, Response<List<Camarero>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(ListadoActivity.this,"Respuesta de Servidor no satisfactoria", Toast.LENGTH_LONG);
                    return;
                }

                List<Camarero> camareros = response.body();
                CamareroListAdapter adaptador = new CamareroListAdapter(ListadoActivity.this, camareros);
                listaResultado.setAdapter(adaptador);
            }

            @Override
            public void onFailure(Call<List<Camarero>> call, Throwable t) {
                Toast.makeText(ListadoActivity.this,t.getMessage(), Toast.LENGTH_LONG);
            }
        });
    }

    private void pintarProductos(){

        Call<List<Producto>> call = jsonPlaceHolderApi.getProductos();

        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(ListadoActivity.this,"Respuesta de Servidor no satisfactoria", Toast.LENGTH_LONG);
                    return;
                }

                List<Producto> productos = response.body();
                ProductoListAdapter adaptador = new ProductoListAdapter(ListadoActivity.this, productos);
                listaResultado.setAdapter(adaptador);
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Toast.makeText(ListadoActivity.this,t.getMessage(), Toast.LENGTH_LONG);
            }
        });
    }

    private void pintarPedidos(){
        Call<List<Pedido>> call = jsonPlaceHolderApi.getPedidos();

        call.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(ListadoActivity.this,"Respuesta de Servidor no satisfactoria", Toast.LENGTH_LONG);
                    return;
                }

                List<Pedido> pedidos = response.body();
                PedidoListAdapter adaptador = new PedidoListAdapter(ListadoActivity.this, pedidos);
                listaResultado.setAdapter(adaptador);
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Toast.makeText(ListadoActivity.this,t.getMessage(), Toast.LENGTH_LONG);
            }
        });
    }
}
