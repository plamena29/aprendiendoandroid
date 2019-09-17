package com.canplimplam.despensainteligente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.canplimplam.despensainteligente.adaptadores.DespensaListAdapter;
import com.canplimplam.despensainteligente.adaptadores.ListasCompraMasterAdapter;
import com.canplimplam.despensainteligente.model.ListaCompra;
import com.canplimplam.despensainteligente.model.Producto;
import com.canplimplam.despensainteligente.services.DespensaServices;
import com.canplimplam.despensainteligente.services.DespensaServicesSQLite;
import com.canplimplam.despensainteligente.services.ListaCompraServices;
import com.canplimplam.despensainteligente.services.ListaCompraServicesSQLite;

import java.text.ParseException;
import java.util.List;

public class GestionarListasCompraActivity extends AppCompatActivity {

    private EditText editNombreNuevaListaCompra;
    private Button botonCrearNuevaListaCompra;
    private Button botonEliminarListaCompra;
    private Button botonEditarListaCompra;
    private Button botonEditarProductosListaCompra;
    private ListView resultadoListasCompra;

    private List<ListaCompra> listasCompraMaster;
    private ListaCompra listaCompraSeleccionada;
    private ListaCompraServices listaCompraServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_listas_compra);

        listaCompraServices = new ListaCompraServicesSQLite(this);

        editNombreNuevaListaCompra = (EditText) findViewById(R.id.idNombreNuevaListaCompra);
        botonCrearNuevaListaCompra = (Button) findViewById(R.id.idBotonCrearNuevaListaCompra);
        resultadoListasCompra = (ListView) findViewById(R.id.idListaComprasMaestro);
        botonEliminarListaCompra = (Button) findViewById(R.id.idBotonEliminarListaCompra);
        botonEditarListaCompra = (Button) findViewById(R.id.idBotonEditarListaCompra);
        botonEditarProductosListaCompra = (Button) findViewById(R.id.idBotonEditarProductosListaCompra);

        listaCompraSeleccionada = new ListaCompra();
        listaCompraSeleccionada.setCodigo(-1);

        //Mostramos todas las Listas de Compra existentes
        refreshLista();

        //Listener en el botón para crear nueva lista de compra
        botonCrearNuevaListaCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int codigo = -1;
                String nombre = editNombreNuevaListaCompra.getText().toString();

                if(!nombre.equals("")) {
                    ListaCompra listaCompra = new ListaCompra(codigo, nombre);
                    listaCompraServices.updateListaCompra(listaCompra);

                    refreshLista();
                }
            }
        });

        //Item Listener para el resultado de listas de compra. Setearemos la lista Seleccionada
        //Luego podremos acceder a Edit o Borrar
        resultadoListasCompra.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listaCompraSeleccionada = (ListaCompra) resultadoListasCompra.getItemAtPosition(position);
            }
        });

        botonEliminarListaCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listaCompraSeleccionada.getCodigo() != -1){
                    listaCompraServices.deleteListaCompra(listaCompraSeleccionada.getCodigo());
                    refreshLista();
                }
            }
        });

        botonEditarListaCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listaCompraSeleccionada.getCodigo() != -1){
                    Intent intent = new Intent(GestionarListasCompraActivity.this, EditarListaCompraMaestroActivity.class);
                    intent.putExtra("LISTA_COMPRA_ID", listaCompraSeleccionada.getCodigo());
                    startActivity(intent);
                }
            }
        });

        botonEditarProductosListaCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GestionarListasCompraActivity.this, EditarListaCompraActivity.class);
                intent.putExtra("LISTA_COMPRA_ID", listaCompraSeleccionada.getCodigo());
                startActivity(intent);
            }
        });
    }

    //Método para refrescar el resultado de listas de compra existentes
    private void refreshLista(){
        listasCompraMaster = listaCompraServices.getAllListasCompraMaster();
        ListasCompraMasterAdapter adaptador = new ListasCompraMasterAdapter(GestionarListasCompraActivity.this, listasCompraMaster);
        resultadoListasCompra.setAdapter(adaptador);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        refreshLista();
    }
}
