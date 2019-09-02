package com.canplimplam.despensainteligente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.canplimplam.despensainteligente.adaptadores.DespensaListAdapter;
import com.canplimplam.despensainteligente.model.ListaCompra;
import com.canplimplam.despensainteligente.model.Producto;
import com.canplimplam.despensainteligente.services.DespensaServices;
import com.canplimplam.despensainteligente.services.DespensaServicesSQLite;
import com.canplimplam.despensainteligente.services.ListaCompraServices;
import com.canplimplam.despensainteligente.services.ListaCompraServicesSQLite;

import java.util.List;

public class EditarListaCompraActivity extends AppCompatActivity {

    private String strNombreListaCompra;
    TextView nombreListaCompra;
    private ListaCompra listaCompra;
    private EditText buscador;
    private ListView listaBusquedaDespensa;
    private List<Producto> resultadoBusquedaEnDespensa;
    private Button botonAnadirProductoListaCompra;

    private DespensaServices despensaServices;
    private ListaCompraServices listaCompraServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_lista_compra);

        despensaServices = new DespensaServicesSQLite(this);
        listaCompraServices = new ListaCompraServicesSQLite(this);

        nombreListaCompra = (TextView) findViewById(R.id.idNombreListaCompra);
        buscador = (EditText) findViewById(R.id.idBuscadorProductoListaCompra);
        listaBusquedaDespensa = (ListView) findViewById(R.id.idListaBusquedaDespensa);
        botonAnadirProductoListaCompra = (Button) findViewById(R.id.idBotonAnadirProductoEnLista);

        Bundle extras = getIntent().getExtras();
        int codigoListaCompra = extras.getInt("LISTA_COMPRA_ID");
        listaCompra = listaCompraServices.readListaCompra(codigoListaCompra);

        nombreListaCompra.setText(listaCompra.getNombre());

        buscador.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.equals("")){
                    resultadoBusquedaEnDespensa = despensaServices.getAllDespensa();
                }
                else{
                    resultadoBusquedaEnDespensa = despensaServices.getByTextDespensa(s.toString());
                }

                DespensaListAdapter adaptador = new DespensaListAdapter(EditarListaCompraActivity.this, resultadoBusquedaEnDespensa);
                listaBusquedaDespensa.setAdapter(adaptador);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listaBusquedaDespensa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Producto producto = (Producto) listaBusquedaDespensa.getItemAtPosition(position);
                buscador.setText(producto.getNombre());
            }
        });

        botonAnadirProductoListaCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
