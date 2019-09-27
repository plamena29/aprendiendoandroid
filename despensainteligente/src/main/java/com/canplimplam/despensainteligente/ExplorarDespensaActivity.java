package com.canplimplam.despensainteligente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.canplimplam.despensainteligente.adaptadores.DetalleDespensaEditAdapter;
import com.canplimplam.despensainteligente.model.Despensa;
import com.canplimplam.despensainteligente.model.Producto;
import com.canplimplam.despensainteligente.services.DespensaServices;
import com.canplimplam.despensainteligente.services.DespensaServicesSQLite;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ExplorarDespensaActivity extends AppCompatActivity {
    //Campos refinados
    //Campos en pantalla
    private EditText buscador;
    private ListView listaResultado;
    private Button botonGuardar;

    //Variables de instancia según modelo
    private Despensa despensa;
    private DetalleDespensaEditAdapter adaptador;

    //Services
    private DespensaServices despensaServices;

    //Variables de ayuda
    private List<Producto> productosDespensa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorar_despensa);

        despensaServices = new DespensaServicesSQLite(this);
        buscador = (EditText) findViewById(R.id.idFiltroProductoDespensa);
        listaResultado = (ListView) findViewById(R.id.idListaDespensa);
        botonGuardar = (Button) findViewById(R.id.idBotonGuardarCambiosDespensa);

        //Seteamos la despensa
        productosDespensa = despensaServices.getAllDespensa();
        Map<String,Producto> mapDespensa = new TreeMap<String,Producto>();
        for(Producto producto: productosDespensa){
            mapDespensa.put(producto.getNombre(), producto);
        }
        despensa = new Despensa();
        despensa.setProductos(mapDespensa);

        //Seteamos el adaptador
        adaptador = new DetalleDespensaEditAdapter(ExplorarDespensaActivity.this, despensa, productosDespensa);
        listaResultado.setAdapter(adaptador);

        //Actualizmos la lista mostrada en función del filtro.
        //Buscamos en el Map del adaptador, no en SQLite
        buscador.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.equals("")){
                    productosDespensa = adaptador.getAllDespensa();
                }
                else{
                    productosDespensa = adaptador.getByTextDespensa(s.toString());
                }

                adaptador.ordenarLista(productosDespensa);
                adaptador.notifyDataSetChanged();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Actualizamos SQLite con lo que haya en el Map del adaptador
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Producto> despensaActual = despensaServices.getAllDespensa();
                List<Producto> despensaActualizada = adaptador.getAllDespensa();

                //Eliminamos de SQLite los productos que no estén en el Map del adaptador
                for(Producto productoOriginal: despensaActual){
                    boolean encontrado = false;
                    for(Producto productoMap: despensaActualizada){
                        if(productoOriginal.getNombre().equals(productoMap.getNombre())){
                            encontrado = true;
                        }
                    }
                    if(!encontrado){
                        despensaServices.deleteProductoDespensa(productoOriginal.getCodigo());
                    }
                }

                //Guardamos/actualizamos los productos que hay en el Map
                for(Producto productoMap: despensaActualizada){
                    despensaServices.updateProductoDespensa(productoMap);
                }

            }
        });
    }
}
