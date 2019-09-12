package com.canplimplam.myviewselectornumero;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyValueSelector extends ConstraintLayout {

    private ImageView botonMenos;
    private TextView valorView;
    private int valor;
    private ImageView botonMas;

    private View view;

    public MyValueSelector(Context context) {
       super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.my_value_selector, null);

        botonMenos = (ImageView) view.findViewById(R.id.idBotonMenos);
        valorView = (TextView) view.findViewById(R.id.idValor);
        botonMas = (ImageView) view.findViewById(R.id.idBotonMas);

        valorView.setText("2");
        valor = 2;
        botonMenos.setImageResource(R.drawable.botonmenos);
        botonMas.setImageResource(R.drawable.botonmas);

        botonMas.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                valor++;
                valorView.setText("" + valor);
            }
        });

        botonMenos.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valor>0){
                    valor --;
                    valorView.setText("" + valor);
                }
            }
        });
    }

    public View getView(){
        return view;
    }

    public void setValor(int valor){
        this.valor = valor;
        valorView.setText(valor);
    }

}
