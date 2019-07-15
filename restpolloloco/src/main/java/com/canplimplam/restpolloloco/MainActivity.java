package com.canplimplam.restpolloloco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.canplimplam.restpolloloco.modelo.Camarero;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonCamareros = (Button) findViewById(R.id.idBotonCamareros);
        Button botonProductos = (Button) findViewById(R.id.idBotonProductos);
        Button botonPedidos = (Button) findViewById(R.id.idBotonPedidos);
        Button botonAltaCamarero = (Button) findViewById(R.id.idBotonAltaCamarero);
        Button botonAltaProducto = (Button) findViewById(R.id.idBotonAltaProducto);
        Button botonLineasPedido = (Button) findViewById(R.id.idBotonLineas);

        //seteamos listeners
        botonCamareros.setOnClickListener(this);
        botonProductos.setOnClickListener(this);
        botonPedidos.setOnClickListener(this);
        botonAltaCamarero.setOnClickListener(this);
        botonAltaProducto.setOnClickListener(this);
        botonLineasPedido.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String botonPulsado = v.getTag().toString();
        Intent intent;

        switch (botonPulsado){
            case "0":
                intent = new Intent(v.getContext(), ListadoActivity.class);
                intent.putExtra("CONTENIDO_LISTADO", "CAMAREROS");
                startActivity(intent);
                break;
            case "1":
                intent = new Intent(v.getContext(), ListadoActivity.class);
                intent.putExtra("CONTENIDO_LISTADO", "PRODUCTOS");
                startActivity(intent);
                break;
            case "2":
                intent = new Intent(v.getContext(), ListadoActivity.class);
                intent.putExtra("CONTENIDO_LISTADO", "PEDIDOS");
                startActivity(intent);
                break;
            case "3":
                intent = new Intent(v.getContext(), AltaCamareroActivity.class);
                startActivity(intent);
                break;
            case "4":
                intent = new Intent(v.getContext(), AltaProductoActivity.class);
                startActivity(intent);
                break;
            case "5":
                intent = new Intent(v.getContext(), LineaPedidoActivity.class);
                startActivity(intent);
                break;
        }

    }
}
