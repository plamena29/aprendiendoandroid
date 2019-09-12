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

        /*
        MiBoton mb = new MiBoton(this);
        mb.setText("hola");
        */

        Log.d("**", "contexto desde mian activity: " + this.getApplicationContext());
        MyValueSelector mvs = new MyValueSelector(this);
        MyValueSelector mvs2 = new MyValueSelector(this);

        View v = mvs.getView();
        View v2 = mvs2.getView();
       // mvs.setValor(4);

      setContentView(v);
        setContentView(v);

        Log.d("**", "despues de set text");
       // myValueSelector = new MyValueSelector(this);
       // myValueSelector.setValor(4);

       // viewSelector = findViewById(R.id.idMyValueSelector);
      //  viewSelector = myValueSelector.getView();
    }
}
