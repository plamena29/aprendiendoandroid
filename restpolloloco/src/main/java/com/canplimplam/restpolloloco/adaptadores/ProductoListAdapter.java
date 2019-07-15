package com.canplimplam.restpolloloco.adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.canplimplam.restpolloloco.R;
import com.canplimplam.restpolloloco.modelo.Producto;

import java.util.List;

public class ProductoListAdapter extends BaseAdapter {

    private LayoutInflater inflater = null;
    private List<Producto> productos;
    private Context contexto;

    public ProductoListAdapter (Context contexto, List<Producto> productos) {
        this.contexto = contexto;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
        this.productos = productos;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.row_model_producto, null);

        //Recoger todas las vistas de ese layout..

        TextView codigoProducto = (TextView) vista.findViewById(R.id.idCodigoProductoRowAdaptador);
        TextView nombreProducto = (TextView) vista.findViewById(R.id.idNombreProductoRowAdaptador);
        TextView precioProducto = (TextView) vista.findViewById(R.id.idPrecioProductoRowAdaptador);

        Producto producto = productos.get(position);
        codigoProducto.setText(String.valueOf(producto.getCodigo()));
        nombreProducto.setText(producto.getNombre());
        precioProducto.setText(String.valueOf(producto.getPrecio()));

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
        return productos.get(position).getCodigo();
    }
}
