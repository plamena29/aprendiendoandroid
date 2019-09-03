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
import com.canplimplam.despensainteligente.adaptadores.DetalleListaCompraAdapter;
import com.canplimplam.despensainteligente.model.ListaCompra;
import com.canplimplam.despensainteligente.model.Producto;
import com.canplimplam.despensainteligente.services.DespensaServices;
import com.canplimplam.despensainteligente.services.DespensaServicesSQLite;
import com.canplimplam.despensainteligente.services.ListaCompraServices;
import com.canplimplam.despensainteligente.services.ListaCompraServicesSQLite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EditarListaCompraActivity extends AppCompatActivity {

    private TextView nombreListaCompra;
    private ListaCompra listaCompra;
    private EditText buscador;
    private ListView listaBusquedaDespensa;
    private List<Producto> resultadoBusquedaEnDespensa;
    private ListView listaProductosEnListaCompra;

    private Button botonAnadirProductoListaCompra;

    private DespensaServices despensaServices;
    private ListaCompraServices listaCompraServices;
    private int codigoListaCompra;

    private Date caducidad;
    {
        try {
            caducidad = SDF_EUROPE.parse("31/12/9999");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static final SimpleDateFormat SDF_EUROPE = new SimpleDateFormat("dd/MM/yyyy");

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
        listaProductosEnListaCompra = (ListView) findViewById(R.id.idListaProductosListaCompra);

        Bundle extras = getIntent().getExtras();
        codigoListaCompra = extras.getInt("LISTA_COMPRA_ID");
        listaCompra = listaCompraServices.readListaCompra(codigoListaCompra);

        nombreListaCompra.setText(listaCompra.getNombre());
        refreshListaProductos();

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
                Producto producto = new Producto(-1, buscador.getText().toString(), 1, caducidad);
                listaCompraServices.crearProductoListaCompra(codigoListaCompra, producto);
            }
        });
    }

    private void refreshListaProductos(){
        List<Producto> productosEnListaCompra = listaCompraServices.getAllProductosListaCompra(codigoListaCompra);
        DetalleListaCompraAdapter adaptador = new DetalleListaCompraAdapter(EditarListaCompraActivity.this, resultadoBusquedaEnDespensa);
        listaBusquedaDespensa.setAdapter(adaptador);
    }
}
