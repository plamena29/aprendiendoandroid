package com.canplimplam.customviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private List<Registro> datos;
    private LayoutInflater inflater;

    public ListViewAdapter(Context context, List<Registro> datos){
        this.datos = datos;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.row_layaout, null);

        MiDisplayView mdv = (MiDisplayView) view.findViewById(R.id.idMiDisplayView);
        Button boton = (Button) view.findViewById(R.id.idBoton);
        TextView textView = (TextView) view.findViewById(R.id.idTextView);

        mdv.setCity(datos.get(position).getCity());
        mdv.setCelsius(datos.get(position).getCelsius());
        boton.setText("boton_" + position);
        textView.setText("texto_" + position);
        return view;
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
