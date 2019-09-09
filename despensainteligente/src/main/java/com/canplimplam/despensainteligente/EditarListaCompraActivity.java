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
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class EditarListaCompraActivity extends AppCompatActivity {
    //Campos refinados
    //Campos en pantalla
    private TextView nombreListaCompra;
    private EditText buscador;
    private ListView listaBusquedaDespensa;
    private Button botonAnadirProductoListaCompra;
    private ListView listaProductosEnListaCompra;
    private Button botonActualizarDespensa;
    private Button botonGuardarDetalleListaCompra;

    //Variables de instancia según modelo
    private ListaCompra listaCompra;
    private DetalleListaCompraAdapter adaptador;

    //Services
    private DespensaServices despensaServices;
    private ListaCompraServices listaCompraServices;

    //Variables de ayuda
    private List<Producto> resultadoBusquedaEnDespensa;
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

        //Seteamos los Services
        despensaServices = new DespensaServicesSQLite(this);
        listaCompraServices = new ListaCompraServicesSQLite(this);

        //Identificamos los campos
        nombreListaCompra = (TextView) findViewById(R.id.idNombreListaCompra);
        buscador = (EditText) findViewById(R.id.idBuscadorProductoListaCompra);
        listaBusquedaDespensa = (ListView) findViewById(R.id.idListaBusquedaDespensa);
        botonAnadirProductoListaCompra = (Button) findViewById(R.id.idBotonAnadirProductoEnLista);
        listaProductosEnListaCompra = (ListView) findViewById(R.id.idListaProductosListaCompra);
        botonActualizarDespensa = (Button) findViewById(R.id.idBotonActualizarDespensaDesdeListaCompra);
        botonGuardarDetalleListaCompra = (Button) findViewById(R.id.idBotonGuardarDetalleLista);

        //Recogemos extras y seteamos Lista de Compra
        Bundle extras = getIntent().getExtras();
        int codigoListaCompra = extras.getInt("LISTA_COMPRA_ID");
        listaCompra = listaCompraServices.readListaCompra(codigoListaCompra);
        nombreListaCompra.setText(listaCompra.getNombre());
        refreshListaProductosEnPantalla(codigoListaCompra);

        //Funcionamiento del buscador en despensa
        buscador.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(buscador.getText().toString().equals("")){
                    resultadoBusquedaEnDespensa.clear();
                }
                else{
                    resultadoBusquedaEnDespensa = despensaServices.getByTextDespensa(s.toString());

                }

                DespensaListAdapter adaptadorDespensa = new DespensaListAdapter(EditarListaCompraActivity.this, resultadoBusquedaEnDespensa);
                listaBusquedaDespensa.setAdapter(adaptadorDespensa);
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

        //Añadir producto a la Lista de Compra
        botonAnadirProductoListaCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto nuevoProducto = new Producto(-1, buscador.getText().toString(), 1, caducidad);
                listaCompra.getProductos().add(nuevoProducto);
                adaptador.ordenarLista(listaCompra.getProductos());
                adaptador.notifyDataSetChanged();
            }
        });

        //Actualizar la despensa con el detalle de la lista de compra
        botonActualizarDespensa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Recogemos los códigos de los productos existentes desde la despensa parahacer el matching correcto
                List<Producto> productosConCodigoActualizado = adaptador.getProductos();
                for(Producto producto: productosConCodigoActualizado){
                    int codigoExistente = despensaServices.validarProductoPorNombre(producto.getNombre());
                    producto.setCodigo(codigoExistente);
                    Log.d("**", "código: " + producto.getCodigo() + " - producto: " + producto.getNombre());
                }

                //Actualizamos la lista de compra en memoria y en base de datos
                listaCompra.setProductos(productosConCodigoActualizado);
                listaCompraServices.updateProductosListaCompra(listaCompra);

                //Actualizamos la despensa desde la lista de compra ajustando las cantidades
                despensaServices.actualizarDespensaDesdeListaCompra(listaCompra.getProductos());
            }
        });

        //Guardar el detalle de los productos en SQLite
        botonGuardarDetalleListaCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaCompra.setProductos(adaptador.getProductos());
                listaCompraServices.updateProductosListaCompra(listaCompra);
            }
        });

    }

    private void refreshListaProductosEnPantalla(int codigoListaCompra){
        listaCompra.setProductos(listaCompraServices.getAllProductosListaCompra(codigoListaCompra));
        adaptador = new DetalleListaCompraAdapter(EditarListaCompraActivity.this, listaCompra);
        listaProductosEnListaCompra.setAdapter(adaptador);
    }

}
