package com.canplimplam.despensainteligente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

public class EditarListaCompraActivity extends AppCompatActivity {

    private String strNombreListaCompra;
    TextView nombreListaCompra;
    private SearchView buscador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_lista_compra);

        nombreListaCompra = (TextView) findViewById(R.id.idNombreListaCompra);
        buscador = (SearchView) findViewById(R.id.idBuscadorProducto);

        nombreListaCompra.setText("Mi lista básica");

    }
}
