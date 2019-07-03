package com.canplimplam.lecturaconstantes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.canplimplam.lecturaconstantes.model.Lectura;
import com.canplimplam.lecturaconstantes.model.LecturaServices;
import com.canplimplam.lecturaconstantes.model.LecturaServicesSQLite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Adaptador extends BaseAdapter {

    private LayoutInflater inflater = null;
    private List<Lectura> lecturas;
    private Context contexto;

    public Adaptador (Context contexto) {
        this.contexto = contexto;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);

        LecturaServices lecturaServices = new LecturaServicesSQLite(contexto);

        // Aprovechando que pasamos por aquí...

       /*
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy H:mm");
        String strFecha1 = "26/06/2019 10:30";
        String strFecha2 = "26/06/2019 12:00";
        Date fecha1 = new Date();
        Date fecha2 = new Date();
        try {
            fecha1 = sdf.parse(strFecha1);
            fecha2 = sdf.parse(strFecha2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        lecturas = lecturaServices.getBetweenDates(fecha1, fecha2);
*/
       lecturas = lecturaServices.getAll();
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

        Lectura lectura = lecturas.get(i);
        fechaDia.setText(sdfDia.format(lectura.getFechaHora()));
        fechaHora.setText(sdfHora.format(lectura.getFechaHora()));
        peso.setText(String.valueOf(lectura.getPeso()));
        diastolica.setText(String.valueOf(lectura.getDiastolica()));
        sistolica.setText(String.valueOf(lectura.getSistolica()));

      //  LinearLayout linearLayout = new LinearLayout(contexto);
      //  TextView tv1 = new TextView(contexto);
      //  TextView tv2 = new TextView(contexto);

      //  tv1.setText(String.valueOf(lectura.getDiastolica()));
      //  tv2.setText(String.valueOf(lectura.getSistolica()));
      //  linearLayout.addView(tv1);
      //  linearLayout.addView(tv2);

      //  return linearLayout;

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
