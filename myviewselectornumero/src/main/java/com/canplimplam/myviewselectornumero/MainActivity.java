package com.canplimplam.myviewselectornumero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

   // private MyValueSelector myValueSelector;
   // private View viewSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("**", "main activity");

        MiBoton mb = new MiBoton(this);
        mb.setText("hola");

        MyValueSelector mvs = new MyValueSelector(this);


       // mvs.setValor(4);
        setContentView(mvs);
     //   mvs.valorView.setText("hola");

        Log.d("**", "despues de set text");
       // myValueSelector = new MyValueSelector(this);
       // myValueSelector.setValor(4);

       // viewSelector = findViewById(R.id.idMyValueSelector);
      //  viewSelector = myValueSelector.getView();
    }
}
