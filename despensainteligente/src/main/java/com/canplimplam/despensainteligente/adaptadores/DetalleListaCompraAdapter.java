package com.canplimplam.despensainteligente.adaptadores;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.canplimplam.despensainteligente.R;
import com.canplimplam.despensainteligente.model.ListaCompra;
import com.canplimplam.despensainteligente.model.Producto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class DetalleListaCompraAdapter extends BaseAdapter {

    private LayoutInflater inflater = null;
    private ListaCompra listaCompra;
    private Context contexto;
    private static final SimpleDateFormat SDF_EUROPE = new SimpleDateFormat("dd/MM/yyyy");
    private int currentlyFocusedRow = -1;

    public DetalleListaCompraAdapter (Context contexto, ListaCompra listaCompra) {
        this.contexto = contexto;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
        this.listaCompra = listaCompra;
        ordenarLista(listaCompra.getProductos());
    }

    public void ordenarLista(List<Producto> productos){
        Map<String,Producto> mapaProductosEnListaCompra = new TreeMap<String,Producto>();
        List<Producto> listaOrdenada = new ArrayList<>();
        for(Producto producto: productos){
            mapaProductosEnListaCompra.put(producto.getNombre(), producto);
        }
        Set<String> llaves = mapaProductosEnListaCompra.keySet();
        for(String llave: llaves){
            Producto producto = mapaProductosEnListaCompra.get(llave);
            listaOrdenada.add(producto);
        }
        listaCompra.setProductos(listaOrdenada);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.row_model_producto_lista_compra, null);

        //Recoger todas las vistas de ese layout..
        final TextView nombreProducto = (TextView) vista.findViewById(R.id.idNombreProductoEnListaCompra);
        final EditText cantidadProducto = (EditText) vista.findViewById(R.id.idCantidadProductoEnListaCompra);
        EditText caducidadProducto = (EditText) vista.findViewById(R.id.idCaducidadEnListaCompra);

        Producto producto = listaCompra.getProductos().get(position);
        nombreProducto.setText(producto.getNombre());
        cantidadProducto.setText(String.valueOf(producto.getCantidad()));
        caducidadProducto.setText(SDF_EUROPE.format(producto.getCaducidad()));

        cantidadProducto.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listaCompra.getProductos().get(position).setCantidad(Integer.parseInt(cantidadProducto.getText().toString()));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        cantidadProducto.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(currentlyFocusedRow == -1){
                    currentlyFocusedRow = position;
                }
                if ((position != currentlyFocusedRow)){ // hemos cambiado
                    Log.d("**", "producto: " + listaCompra.getProductos().get(currentlyFocusedRow).getNombre());
                    currentlyFocusedRow = position;
                }
            }
        });

        return vista;
    }

    @Override
    public int getCount() {
        return listaCompra.getProductos().size();
    }

    @Override
    public Object getItem(int position) {
        return listaCompra.getProductos().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
