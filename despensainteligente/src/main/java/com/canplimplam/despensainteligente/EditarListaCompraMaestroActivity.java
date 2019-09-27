package com.canplimplam.despensainteligente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.canplimplam.despensainteligente.model.ListaCompra;
import com.canplimplam.despensainteligente.services.ListaCompraServices;
import com.canplimplam.despensainteligente.services.ListaCompraServicesSQLite;

public class EditarListaCompraMaestroActivity extends AppCompatActivity {

    TextView editNombreListaCompra;
    Button botonGuardar;
    private ListaCompraServices listaCompraServices;
    private String nombre;
    private int codigoListaCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_lista_compra_maestro);

        listaCompraServices = new ListaCompraServicesSQLite(this);

        editNombreListaCompra = (EditText) findViewById(R.id.idNombreListaCompraEditar);
        botonGuardar = (Button) findViewById(R.id.idBotonGuardarListaCompraMaestro);

        Bundle extras = getIntent().getExtras();
        codigoListaCompra = extras.getInt("LISTA_COMPRA_ID");
        nombre = listaCompraServices.readListaCompra(codigoListaCompra).getNombre();
        editNombreListaCompra.setText(nombre);

        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = editNombreListaCompra.getText().toString();
                if(!nombre.equals("")){
                    ListaCompra listaCompra = new ListaCompra(codigoListaCompra, nombre);
                    listaCompraServices.updateListaCompra(listaCompra);
                    Intent intent = new Intent(EditarListaCompraMaestroActivity.this, GestionarListasCompraActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
