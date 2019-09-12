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
import com.canplimplam.despensainteligente.model.Despensa;
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

public class DetalleDespensaEditAdapter extends BaseAdapter {

    private LayoutInflater inflater = null;
    private Despensa despensa;                  //El Map servirá para almacenar los futuros cambios que persistiran en SQLite tras pulsar Guardar
    private List<Producto> productosOrdenados; //Sirve solo para visualizar filtro y resultados
    private Context contexto;
    private static final SimpleDateFormat SDF_EUROPE = new SimpleDateFormat("dd/MM/yyyy");

    public DetalleDespensaEditAdapter(Context contexto, Despensa despensa, List<Producto> productosOrdenados){
        this.contexto = contexto;
        this.despensa = despensa;
        this.productosOrdenados = productosOrdenados;

        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    public void ordenarLista(List<Producto> productos){
        productosOrdenados.clear();
        productosOrdenados = productos;
    }

    public List<Producto> getAllDespensa(){
        List<Producto> productosFiltrados = new ArrayList<>();
        Set<String> llaves = despensa.getProductos().keySet();
        for(String llave: llaves){
            productosFiltrados.add(despensa.getProductos().get(llave));
        }
        return productosFiltrados;
    }

    public List<Producto> getByTextDespensa(String str){
        List<Producto> productosFiltrados = new ArrayList<>();
        Set<String> llaves = despensa.getProductos().keySet();
        for(String llave: llaves){
            if(llave.contains(str)){
                productosFiltrados.add(despensa.getProductos().get(llave));
            }
        }
        return productosFiltrados;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //REUTILIZAMOS EL LAYOUT PARA EL ADAPTADOR DEL DETALLE DE LA LISTA DE COMPRA
        final View vista = inflater.inflate(R.layout.row_model_producto_lista_compra, null);

        //Recoger todas las vistas de ese layout..
        final ImageView imageBorrarProducto = (ImageView) vista.findViewById(R.id.idImageBorrarProductoListaCompra);
        final TextView nombreProducto = (TextView) vista.findViewById(R.id.idNombreProductoEnListaCompra);
        final EditText cantidadProducto = (EditText) vista.findViewById(R.id.idCantidadProductoEnListaCompra);
        final EditText caducidadProducto = (EditText) vista.findViewById(R.id.idCaducidadEnListaCompra);

        Producto producto = productosOrdenados.get(position);
        imageBorrarProducto.setImageResource(R.drawable.botoneliminar);
        nombreProducto.setText(producto.getNombre());
        cantidadProducto.setText(String.valueOf(producto.getCantidad()));
        caducidadProducto.setText(SDF_EUROPE.format(producto.getCaducidad()));

        //Listener para eliminar producto - lo sacamos del Map, no actualizaremos SQLite hasta que no se clicke en Gurdar
        imageBorrarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("**", "borrar: " + productosOrdenados.get(position).getNombre());
                despensa.getProductos().remove(productosOrdenados.get(position).getNombre());
                productosOrdenados.remove(position);
                notifyDataSetChanged();
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
        return productosOrdenados.size();
    }

    @Override
    public Object getItem(int position) {
        return productosOrdenados.get(position);
    }

    @Override
    public long getItemId(int position) {
        return productosOrdenados.get(position).getCodigo();
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
                productosOrdenados.get(position).setCaducidad(fecha);
                despensa.getProductos().put(productosOrdenados.get(position).getNombre(), productosOrdenados.get(position));
            }
        });

        newFragment.show(((FragmentActivity)contexto).getSupportFragmentManager(), "datePicker");
    }
}
