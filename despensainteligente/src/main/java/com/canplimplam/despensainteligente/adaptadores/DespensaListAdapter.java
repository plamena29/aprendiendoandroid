package com.canplimplam.despensainteligente.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.canplimplam.despensainteligente.R;
import com.canplimplam.despensainteligente.model.Producto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DespensaListAdapter extends BaseAdapter {

    private LayoutInflater inflater = null;
    private List<Producto> productos;
    private Context contexto;
    private static final SimpleDateFormat SDF_EUROPE = new SimpleDateFormat("dd/MM/yyyy");

    public DespensaListAdapter (Context contexto, List<Producto> productos) {
        this.contexto = contexto;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
        this.productos = productos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.row_model_producto_despensa, null);

        //Recoger todas las vistas de ese layout..

        TextView nombreProducto = (TextView) vista.findViewById(R.id.idProductoRowDespensa);
        TextView cantidadProducto = (TextView) vista.findViewById(R.id.idCantidadRowDespensa);
        TextView caducidadProducto = (TextView) vista.findViewById(R.id.idCaducidadRowDespensa);

        Producto producto = productos.get(position);
        nombreProducto.setText(producto.getNombre());
        cantidadProducto.setText(String.valueOf(producto.getCantidad()));
        caducidadProducto.setText(SDF_EUROPE.format(producto.getCaducidad()));

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
