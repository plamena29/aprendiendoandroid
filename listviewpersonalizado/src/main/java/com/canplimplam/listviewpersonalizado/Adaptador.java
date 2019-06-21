package com.canplimplam.listviewpersonalizado;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
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
        TextView titulo = (TextView) vista.findViewById(R.id.idTitulo);
        TextView macronutriente = (TextView) vista.findViewById(R.id.idMacronutriente);
        TextView cantidad = (TextView) vista.findViewById(R.id.idCantidad);
        TextView tienda = (TextView) vista.findViewById(R.id.idTienda);
        ImageView imagen = (ImageView) vista.findViewById(R.id.idImagen);
        RatingBar calificacion = (RatingBar) vista.findViewById(R.id.idRatingBar);

        //Colocamos valores
        titulo.setText(datos[i][0]);
        macronutriente.setText(datos[i][1]);
        cantidad.setText(datos[i][2]);
        tienda.setText("Tienda: " + datos[i][3]);
        imagen.setImageResource(datosImg[i]);
        calificacion.setProgress(Integer.valueOf(datos[i][4]));

        //Click con ficha completa
        imagen.setTag(datosImg[i]);

        imagen.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent  = new Intent(contexto, VisorImagen.class);
                intent.putExtra("IMG", (Integer) v.getTag());
                contexto.startActivity(intent);
            }
        });

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
