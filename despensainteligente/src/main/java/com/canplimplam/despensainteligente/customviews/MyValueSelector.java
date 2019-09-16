package com.canplimplam.despensainteligente.customviews;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.canplimplam.despensainteligente.R;

public class MyValueSelector extends ConstraintLayout {

    private ImageView botonMenos;
    private TextView valorView;
    private int valor;
    private ImageView botonMas;

    public MyValueSelector(Context context, AttributeSet attrs) {
        super(context, attrs);

        //Inflar el layout
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        inflater.inflate(R.layout.my_value_selector, this);

        //Obtenemos la referecnias a las views
        botonMenos = (ImageView) findViewById(R.id.idBotonMenos);
        valorView = (TextView) findViewById(R.id.idValor);
        botonMas = (ImageView) findViewById(R.id.idBotonMas);

        //Seteamos las imagenes
        Matrix mat = new Matrix();
        Bitmap bMap = BitmapFactory.decodeResource(getResources(),R.drawable.botonmas);
        mat.postRotate(180);
        Bitmap bMapRotate = Bitmap.createBitmap(bMap, 0, 0,bMap.getWidth(),bMap.getHeight(), mat, true);

        botonMenos.setImageBitmap(bMapRotate);
        botonMas.setImageResource(R.drawable.botonmas);

        botonMas.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                valor++;
                valorView.setText("" + valor);
                valorView.setTextColor(Color.BLACK);
            }
        });

        botonMenos.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valor>0){
                    valor --;
                    valorView.setText("" + valor);
                }
                if(valor == 0){
                    valorView.setTextColor(Color.RED);
                }
            }
        });
    }

    public void setValor(int valor){
        this.valor = valor;
        valorView.setText("" + valor);
        if(valor == 0){
            valorView.setTextColor(Color.RED);
        }else{
            valorView.setTextColor(Color.BLACK);
        }
    }
}
