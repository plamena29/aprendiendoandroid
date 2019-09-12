package com.canplimplam.myviewselectornumero;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyValueSelector extends ConstraintLayout {


    private ImageView botonMenos;
    TextView valorView;
    private int valor;
    private ImageView botonMas;

    private Context contexto;
    private View view;

    public MyValueSelector(Context context) {
       super(context);
        Log.d("**", "constructior");
        this.contexto = context;
        LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.my_value_selector, null, true);


       botonMenos = (ImageView) findViewById(R.id.idBotonMenos);
        Log.d("**", "" + R.id.idBotonMenos);
      //  botonMas = (ImageView) view.findViewById(R.id.idBotonMas);
       valorView = (TextView) findViewById(R.id.idValor);

        Log.d("**", "" + R.id.idValor);
        valorView.setText("hola");
       //botonMenos.setImageResource(R.drawable.botonmenos);
      //  botonMas.setImageResource(R.drawable.botonmas);

    }

    public void setValor(int valor){
        this.valor = valor;
        Log.d("**", "set valor 1");
       // valorView.setText("hola");
        Log.d("**", "set valor 2");
    }

    /*
    public View getView(){



        Log.d("**", "get view");
        return view;
    }
    */
}
