package com.canplimplam.despensainteligente.adaptadores;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.canplimplam.despensainteligente.R;
import com.canplimplam.despensainteligente.customviews.MyValueSelector;
import com.canplimplam.despensainteligente.model.ListaCompra;
import com.canplimplam.despensainteligente.model.Producto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class DetalleListaCompraAdapter extends BaseAdapter {

    private LayoutInflater inflater = null;
    private ListaCompra listaCompra;
    private Context contexto;
    private static final SimpleDateFormat SDF_EUROPE = new SimpleDateFormat("dd/MM/yyyy");

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

    public List<Producto> getProductos(){
        return listaCompra.getProductos();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.row_model_producto_lista_compra, null);

        //Recoger todas las vistas de ese layout..
        final ImageView imageBorrarProducto = (ImageView) vista.findViewById(R.id.idImageBorrarProductoListaCompra);
        final TextView nombreProducto = (TextView) vista.findViewById(R.id.idNombreProductoEnListaCompra);
        final MyValueSelector cantidadProducto = (MyValueSelector) vista.findViewById(R.id.idCantidadProductoEnListaCompra);
        final EditText caducidadProducto = (EditText) vista.findViewById(R.id.idCaducidadEnListaCompra);

        Producto producto = listaCompra.getProductos().get(position);
        imageBorrarProducto.setImageResource(R.drawable.botoneliminar);
        nombreProducto.setText(producto.getNombre());
        cantidadProducto.setValor(producto.getCantidad());
        caducidadProducto.setText(SDF_EUROPE.format(producto.getCaducidad()));

        //LISTENERS
        imageBorrarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("**", "borrar: " + listaCompra.getProductos().get(position).getNombre());
                listaCompra.getProductos().remove(position);
                notifyDataSetChanged();
            }
        });

        cantidadProducto.setMyValueSelectorListener(new MyValueSelector.MyValueSelectorListener() {
            @Override
            public void onDataLoaded(MyValueSelector mvs) {
                Log.d("**", listaCompra.getProductos().get(position).getNombre());
                int valor = mvs.getValor();
                listaCompra.getProductos().get(position).setCantidad(valor);
            }
        });

        caducidadProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.idCaducidadEnListaCompra:
                        showDatePickerDialog(v, position);
                        break;
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

    private boolean validarFecha(String strFecha){
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.parse(strFecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    private void showDatePickerDialog(final View v, final int position) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 in month because January is zero
                String strMonth;
                month += 1;
                if(month < 10){
                    strMonth = "0" + month;
                }else{
                    strMonth = "" + month;
                }
                String strDay;
                if(day < 10){
                    strDay = "0" + day;
                }else{
                    strDay = "" + day;
                }

                final String selectedDate = strDay + "/" + strMonth + "/" + year;

                //Editamos el valor en el campo de la listView
                EditText caducidad = (EditText) v.findViewById(R.id.idCaducidadEnListaCompra);
                caducidad.setText(selectedDate);

                //Actualizamos la fecha de caducidad en la lista del adaptador
                Date fecha = new Date();
                try {
                    fecha = SDF_EUROPE.parse(selectedDate);
                } catch (ParseException e) {
                    //Toast
                }
                listaCompra.getProductos().get(position).setCaducidad(fecha);
            }
        });

        newFragment.show(((FragmentActivity)contexto).getSupportFragmentManager(), "datePicker");
    }
}
