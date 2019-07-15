package com.canplimplam.restpolloloco.adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.canplimplam.restpolloloco.R;
import com.canplimplam.restpolloloco.modelo.Camarero;

import java.util.List;

public class CamareroListAdapter extends BaseAdapter {

    private LayoutInflater inflater = null;
    private List<Camarero> camareros;
    private Context contexto;

    public CamareroListAdapter (Context contexto, List<Camarero> camareros) {
        this.contexto = contexto;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
        this.camareros = camareros;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.row_model_camarero, null);

        //Recoger todas las vistas de ese layout..

        TextView codigoCamarero = (TextView) vista.findViewById(R.id.idCodigoCamareroRowAdaptador);
        TextView nombreCamarero = (TextView) vista.findViewById(R.id.idNombreCamareroRowAdaptador);

        Camarero camarero = camareros.get(position);
        codigoCamarero.setText(String.valueOf(camarero.getCodigo()));
        nombreCamarero.setText(camarero.getNombre());

        return vista;
    }

    @Override
    public int getCount() {
        return camareros.size();
    }

    @Override
    public Object getItem(int position) {
        return camareros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return camareros.get(position).getCodigo();
    }
}
