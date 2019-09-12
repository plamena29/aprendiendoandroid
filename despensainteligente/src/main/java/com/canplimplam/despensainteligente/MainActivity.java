package com.canplimplam.despensainteligente;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    ImageView imagenApp;
    Button botonCrearProductoDespensa;
    Button botonExplorarDespensa;
    Button botonGestionarListasCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagenApp = (ImageView) findViewById(R.id.idImagenApp);
        botonCrearProductoDespensa = (Button) findViewById(R.id.idBotonCrearProductoDespensa);
        botonExplorarDespensa = (Button) findViewById(R.id.idBotonExplorarDespensa);
        botonGestionarListasCompra = (Button) findViewById(R.id.idBotonGestionarListasCompra);

        imagenApp.setImageResource(R.drawable.nevera);

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

        botonGestionarListasCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GestionarListasCompraActivity.class);
                startActivity(intent);
            }
        });
    }
}
