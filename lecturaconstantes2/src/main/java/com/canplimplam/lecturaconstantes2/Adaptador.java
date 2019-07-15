package com.canplimplam.lecturaconstantes2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.canplimplam.lecturaconstantes2.fragments.HistoricoFragment;
import com.canplimplam.lecturaconstantes2.model.Lectura;
import com.canplimplam.lecturaconstantes2.model.LecturaServices;
import com.canplimplam.lecturaconstantes2.model.LecturaServicesSQLite;

import java.text.SimpleDateFormat;
import java.util.List;

public class Adaptador extends BaseAdapter {

    private LayoutInflater inflater = null;
    private List<Lectura> lecturas;
    private Context contexto;

    public Adaptador (Context contexto, List<Lectura> lecturas) {
        this.contexto = contexto;
        this.lecturas = lecturas;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.lectura_row, null);
        SimpleDateFormat sdfDia = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");

        //Recoger todas las vistas de ese layout..

        TextView fechaDia = (TextView) vista.findViewById(R.id.idFechaDia);
        TextView fechaHora = (TextView) vista.findViewById(R.id.idFechaHora);
        TextView peso = (TextView) vista.findViewById(R.id.idPeso);
        TextView diastolica = (TextView) vista.findViewById(R.id.idDiastolica);
        TextView sistolica = (TextView) vista.findViewById(R.id.idSistolica);
        TextView longitud = (TextView) vista.findViewById(R.id.idLongitud);
        TextView latitud = (TextView) vista.findViewById(R.id.idLatitud);

        Lectura lectura = lecturas.get(i);
        fechaDia.setText(sdfDia.format(lectura.getFechaHora()));
        fechaHora.setText(sdfHora.format(lectura.getFechaHora()));
        peso.setText(String.valueOf(lectura.getPeso()));
        diastolica.setText(String.valueOf(lectura.getDiastolica()));
        sistolica.setText(String.valueOf(lectura.getSistolica()));
        longitud.setText(String.valueOf(lectura.getLongitud()));
        latitud.setText(String.valueOf(lectura.getLatitud()));
        return vista;
    }

    @Override
    public int getCount() {
        return lecturas.size();
    }

    @Override
    public Object getItem(int position) {
        return lecturas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lecturas.get(position).getCodigo();
    }
}
