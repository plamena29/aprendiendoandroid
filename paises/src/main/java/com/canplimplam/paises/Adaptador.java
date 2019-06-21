package com.canplimplam.paises;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adaptador extends BaseAdapter {

    private LayoutInflater inflater = null;
    private Context contexto;
    private String[][] datos;
    private int[] datosImg;

    public Adaptador(Context contexto, String[][] datos, int[] imagenes){
        this.contexto = contexto;
        this.datos = datos;
        this.datosImg = imagenes;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.elemento_lista, null);

        //Identificamos cajas
        TextView titulo = (TextView) vista.findViewById(R.id.idPais);
        TextView continente = (TextView) vista.findViewById(R.id.idContinente);
        ImageView imagen = (ImageView) vista.findViewById(R.id.idBandera);

        //Colocamos valores
        titulo.setText("País: " + datos[i][0]);
        continente.setText("Continente: " + datos[i][1]);
        imagen.setImageResource(datosImg[i]);

        //Click con ficha completa
        imagen.setTag(i);
        return vista;
    }

    @Override
    public int getCount() {
        return datosImg.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
