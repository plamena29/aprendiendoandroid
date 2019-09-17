package com.canplimplam.despensainteligente.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.canplimplam.despensainteligente.R;
import com.canplimplam.despensainteligente.model.ListaCompra;

import java.util.List;

public class ListasCompraMasterAdapter extends BaseAdapter {

    private LayoutInflater inflater = null;
    private List<ListaCompra> listasCompraMaestro;
    private Context contexto;

    public ListasCompraMasterAdapter (Context contexto, List<ListaCompra> listasCompraMaestro) {
        this.contexto = contexto;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
        this.listasCompraMaestro = listasCompraMaestro;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.row_model_lista_compra_master, null);

        //Recoger todas las vistas de ese layout..

        TextView volumenListaCompra = (TextView) vista.findViewById(R.id.idVolumenListaCompraRowMaster);
        TextView nombreListaCompra = (TextView) vista.findViewById(R.id.idNombreListaCompraRowMaster);

        ListaCompra listaCompra = listasCompraMaestro.get(position);
        volumenListaCompra.setText(String.valueOf(listaCompra.getVolumen()));
        nombreListaCompra.setText(listaCompra.getNombre());

        return vista;
    }

    @Override
    public int getCount() {
        return listasCompraMaestro.size();
    }

    @Override
    public Object getItem(int position) {
        return listasCompraMaestro.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listasCompraMaestro.get(position).getCodigo();
    }
}
