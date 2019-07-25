package com.canplimplam.despensainteligente;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button botonCrearProductoDespensa;
    Button botonExplorarDespensa;
    Button botonListaCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonCrearProductoDespensa = (Button) findViewById(R.id.idBotonCrearProductoDespensa);
        botonExplorarDespensa = (Button) findViewById(R.id.idBotonExplorarDespensa);
        botonListaCompra = (Button) findViewById(R.id.idBotonEditarListaCompra);

        botonCrearProductoDespensa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FormularioProductoDespensaActivity.class);
                intent.putExtra("PRODUCT_ID", -1);
                startActivity(intent);
            }
        });

        botonExplorarDespensa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ExplorarDespensaActivity.class);
                startActivity(intent);
            }
        });

        botonListaCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditarListaCompraActivity.class);
                startActivity(intent);
            }
        });
    }
}
