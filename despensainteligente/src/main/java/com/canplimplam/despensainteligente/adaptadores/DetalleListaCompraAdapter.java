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

import com.canplimplam.despensainteligente.R;
import com.canplimplam.despensainteligente.model.Producto;

import java.text.SimpleDateFormat;
import java.util.List;

public class DetalleListaCompraAdapter extends BaseAdapter {

    private LayoutInflater inflater = null;
    private List<Producto> productos;
    private Context contexto;
    private static final SimpleDateFormat SDF_EUROPE = new SimpleDateFormat("dd/MM/yyyy");

    private String productoTest;
    private int posicionItem;

    public DetalleListaCompraAdapter (Context contexto, List<Producto> productos) {
        this.contexto = contexto;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
        this.productos = productos;
    }

    public void setLista(List<Producto> productos){
        this.productos = productos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.row_model_producto_lista_compra, null);

        //Recoger todas las vistas de ese layout..

        TextView nombreProducto = (TextView) vista.findViewById(R.id.idNombreProductoEnListaCompra);
        EditText cantidadProducto = (EditText) vista.findViewById(R.id.idCantidadProductoEnListaCompra);
      //  EditText caducidadProducto = (EditText) vista.findViewById(R.id.idCaducidadEnListaCompra);

        Producto producto = productos.get(position);
        productoTest = producto.getNombre();
        nombreProducto.setText(producto.getNombre());
        cantidadProducto.setText(String.valueOf(producto.getCantidad()));
       // caducidadProducto.setText(SDF_EUROPE.format(producto.getCaducidad()));

        cantidadProducto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("**", "before + " + productos.get(posicionItem).getNombre());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("**", "on + " + productos.get(posicionItem).getNombre());
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("**", "after + " + productos.get(posicionItem).getNombre());
            }
        });

        return vista;
    }

    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public Object getItem(int position) {
        return productos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}