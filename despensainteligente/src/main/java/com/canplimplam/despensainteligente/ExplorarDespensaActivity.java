package com.canplimplam.despensainteligente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.canplimplam.despensainteligente.adaptadores.DespensaListAdapter;
import com.canplimplam.despensainteligente.model.Producto;
import com.canplimplam.despensainteligente.services.DespensaServices;
import com.canplimplam.despensainteligente.services.DespensaServicesSQLite;

import java.util.List;

public class ExplorarDespensaActivity extends AppCompatActivity {

    private EditText buscador;
    private ListView listaResultado;
    private List<Producto> productosDespensa;

    private DespensaServices despensaServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorar_despensa);

        despensaServices = new DespensaServicesSQLite(this);
        buscador = (EditText) findViewById(R.id.idFiltroProductoDespensa);
        listaResultado = (ListView) findViewById(R.id.idListaDespensa);

        productosDespensa = despensaServices.getAllDespensa();
        DespensaListAdapter adaptador = new DespensaListAdapter(ExplorarDespensaActivity.this, productosDespensa);
        listaResultado.setAdapter(adaptador);

        buscador.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.equals("")){
                    productosDespensa = despensaServices.getAllDespensa();
                }
                else{
                    productosDespensa = despensaServices.getByTextDespensa(s.toString());
                }

                DespensaListAdapter adaptador = new DespensaListAdapter(ExplorarDespensaActivity.this, productosDespensa);
                listaResultado.setAdapter(adaptador);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listaResultado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Producto producto = (Producto) listaResultado.getItemAtPosition(position);
                Intent intent = new Intent(ExplorarDespensaActivity.this, FormularioProductoDespensaActivity.class);
                intent.putExtra("PRODUCT_ID", producto.getCodigo());
                Log.d("**", "código producto: " + producto.getCodigo());
                startActivity(intent);
            }
        });
        /*
        buscador.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String texto = v.getText().toString();
                Log.d("**", "texto buscador: " + texto);
                if(texto.equals("")){
                    productosDespensa = despensaServices.getAllDespensa();
                }
                else{
                    productosDespensa = despensaServices.getByTextDespensa(texto);
                }

                DespensaListAdapter adaptador = new DespensaListAdapter(ExplorarDespensaActivity.this, productosDespensa);
                listaResultado.setAdapter(adaptador);

                return true;
            }
        });

        */
    }
}
